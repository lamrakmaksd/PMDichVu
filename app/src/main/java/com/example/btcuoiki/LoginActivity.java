package com.example.btcuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btn_getLogin;
    private TextView tv_dontHaveAnAccount;
    private EditText email, password;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_getLogin = findViewById(R.id.btn_getLogin);
        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);

        btn_getLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập  email và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {

                    String taikhoan = email.getText().toString();
                    String matkhau = password.getText().toString();
                    String session = "";

                    Methods methods = RetrofitClient.getRetrofitHangHoa().create(Methods.class);
                    UserToken userToken = new UserToken(taikhoan, matkhau, session);
                    Call<Token> call = methods.getToken(userToken);
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            Token token = new Token();
                            Log.d(TAG, "onResponse:"+token);
                            String tokenABC = String.valueOf(response.body().getToken());
                            Log.d("iii", tokenABC);
                            if (tokenABC!="null") {
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
//                            Log.e(TAG, "onFailure: " + response.toString());
//                            Toast.makeText(LoginActivity.this, String.valueOf(response.body().getToken()), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {

                            Log.e(TAG, "onFailure: " + t.getMessage());
                            Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        tv_dontHaveAnAccount = findViewById(R.id.tv_dontHaveAnAccount);
        tv_dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }
}