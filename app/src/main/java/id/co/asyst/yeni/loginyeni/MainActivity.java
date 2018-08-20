package id.co.asyst.yeni.loginyeni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.asyst.yeni.loginyeni.model.LoginModel;
import id.co.asyst.yeni.loginyeni.retrofit.ApiClient;
import id.co.asyst.yeni.loginyeni.retrofit.ApiServices;
import id.co.asyst.yeni.loginyeni.retrofit.request.LoginRequest;
import id.co.asyst.yeni.loginyeni.retrofit.response.LoginResponse;
import id.co.asyst.yeni.loginyeni.utility.Constant;
import id.co.asyst.yeni.loginyeni.utility.SessionUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameET, passwordET;
    Button loginBtn;
    String username, password;
    SessionUtils sessionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.username_et);
        passwordET = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);

        sessionUtils = new SessionUtils(this);

        loginBtn.setOnClickListener(this);

        String username = sessionUtils.loadUsername();
        String password = sessionUtils.loadPassword();

        if (sessionUtils.isLogin()) {
            startActivity(new Intent(MainActivity.this, ListActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void getLogin() {

        LoginRequest loginRequest = new LoginRequest();
        LoginModel loginModel = new LoginModel();
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();

        loginRequest.setMethod("getProfileInfo");
        loginModel.setUsername(username);
        loginModel.setPassword(password);

        loginRequest.setParam(loginModel);

        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);

        Call<LoginResponse> call = apiServices.getProfileInfo(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body().getStatus().equalsIgnoreCase("success")) {

                    sessionUtils.saveLogin(username, password);
                    sessionUtils.saveIsLogin(Constant.IS_LOGIN, true);

                    Intent intent = new Intent(MainActivity.this, ListActivity.class);

                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:

                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                if (!TextUtils.isEmpty(username)) {
                    if (!TextUtils.isEmpty(password)) {
                        getLogin();
                    } else {
                        passwordET.setError("Password is required");
                    }
                } else {
                    usernameET.setError("Username is required");
                }

                break;
        }
    }
}
