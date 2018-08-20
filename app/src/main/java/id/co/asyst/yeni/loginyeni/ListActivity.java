package id.co.asyst.yeni.loginyeni;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.asyst.yeni.loginyeni.adapter.PgnAdapter;
import id.co.asyst.yeni.loginyeni.model.PgnModel;
import id.co.asyst.yeni.loginyeni.model.UserModel;
import id.co.asyst.yeni.loginyeni.retrofit.ApiClient;
import id.co.asyst.yeni.loginyeni.retrofit.ApiServices;
import id.co.asyst.yeni.loginyeni.retrofit.request.PgnRequest;
import id.co.asyst.yeni.loginyeni.retrofit.response.PgnResponse;
import id.co.asyst.yeni.loginyeni.utility.Constant;
import id.co.asyst.yeni.loginyeni.utility.SessionUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PgnAdapter pgnAdapter;
    ArrayList<PgnModel> listPgn = new ArrayList<>();
    SessionUtils sessionUtils;
    boolean isLoading = false;
//    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListActivity.this, LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        sessionUtils = new SessionUtils(this);
        pgnAdapter = new PgnAdapter(this, listPgn, new PgnAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(PgnModel pgnModel) {

                Toast.makeText(getApplicationContext(), pgnModel.getCustomer_name(), Toast.LENGTH_SHORT).show();

            }
        });

        getDataPgn();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading) {
//                        progressBar.setVisibility(View.VISIBLE);
                        isLoading = true;
                    }
                }
            }
        });

        recyclerView.setAdapter(pgnAdapter);

    }

    private void getDataPgn() {
//        progressBar.setVisibility(View.INVISIBLE);
//        LoginModel loginModel = new LoginModel();
        PgnModel pgnModel = new PgnModel();
        PgnRequest pgnRequest = new PgnRequest();
        pgnRequest.setMethod("getAllTask");
        final UserModel userModel = new UserModel();
        userModel.setUsername(sessionUtils.loadUsername());

        pgnRequest.setParam(pgnModel);

        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);

        Call<PgnResponse> call = apiServices.getAllTask(pgnRequest);
        call.enqueue(new Callback<PgnResponse>() {
            @Override
            public void onResponse(Call<PgnResponse> call, Response<PgnResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("success")) {

                    if (response.body().getData().size() > 0) {

                        listPgn.addAll(response.body().getData());
                        pgnAdapter.notifyDataSetChanged();

                    }
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<PgnResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout_item_menu:
                sessionUtils.saveIsLogin(Constant.IS_LOGIN, false);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("Are You Sure To Logout?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ListActivity.this, MainActivity.class);

                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("NO", null).show();


                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
