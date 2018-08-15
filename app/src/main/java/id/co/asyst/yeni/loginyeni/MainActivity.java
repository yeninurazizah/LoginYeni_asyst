package id.co.asyst.yeni.loginyeni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameET, passwordET;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.username_et);
        passwordET = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                String user = usernameET.getText().toString();
                String pass = passwordET.getText().toString();
                if (!TextUtils.isEmpty(user)) {
                    if (!TextUtils.isEmpty(pass)) {
                        if (user.equals("admin") && pass.equals("admin")) {
                            Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, ListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username dan PAssword Salah", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        passwordET.setError("Password harus diisi");
                    }
                } else {
                    usernameET.setError("Password harus diisi");
                }

                break;
        }
    }
}
