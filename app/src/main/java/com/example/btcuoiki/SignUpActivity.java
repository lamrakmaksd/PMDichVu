package com.example.btcuoiki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private Context context = SignUpActivity.this;
    private String email;
    private String password;
    private String phone;
    private String name;
    private String address;
    private String username;
    private EditText edt_name, edt_email, edt_password, edt_phone,edt_username,edt_address;
    private Button btn_postSignUp;
    private TextView tv_haveAnAccount;
    private CheckBox checkBoxLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_phone = findViewById(R.id.edt_conformPassword);
        edt_username = findViewById(R.id.edt_gioi_tinh);
        edt_address = findViewById(R.id.edt_address);


        tv_haveAnAccount = findViewById(R.id.tv_haveAnAccount);
        tv_haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        btn_postSignUp = findViewById(R.id.btn_postSignUp);
        btn_postSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_name.getText().toString().isEmpty() || edt_username.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập  đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                else {
                    name=edt_name.getText().toString();
                    email=edt_email.getText().toString();
                    password=edt_password.getText().toString();
                    phone=edt_phone.getText().toString();
                    address=edt_address.getText().toString();
                    username=edt_username.getText().toString();
                    Methods methods = RetrofitClient.getRetrofitHangHoa().create(Methods.class);
                    User user=new User(username,password,name,address,email,phone);
                    Call<Token> call = methods.getSignin(user);
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            String tokenABC = String.valueOf(response.body().getToken());
                            Log.d("iii", tokenABC);
                            if (tokenABC!=null) {
                                Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {

                        }
                    });



                    Intent intentLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intentLogin);
                }
            }
        });
    }
}