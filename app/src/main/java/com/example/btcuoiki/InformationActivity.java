package com.example.btcuoiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    private Context context = InformationActivity.this;
    private String name;
    private String gia;
    private String images;
    private InformationAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private TextView tv_danhgia;
    private static final String TAG = "InformationActivity";
    private ModelHanghoa product;
    private TextView tv_name, tv_gia;
    private ImageView img_name;
    private Button btnTang, btnGiam, btnSoluong;
    Integer tam = 1;
    private CardView layoutCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("idhanghoa", 0);
        Methods methods = RetrofitClient.getRetrofitHangHoa().create(Methods.class);
        Call<ModelHanghoa> call = methods.getProduct(id);

        call.enqueue(new Callback<ModelHanghoa>() {
            @Override
            public void onResponse(Call<ModelHanghoa> call, Response<ModelHanghoa> response) {
                product = response.body();
                Log.e(TAG, "onResponse: "+ product.getTen() );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        name = (product.getTen());
                        gia = (product.getGia());
                        images = (product.getHinhanh());
                        tv_name =findViewById(R.id.tv_name);
                        tv_gia = findViewById(R.id.tv_gia);
                        img_name = findViewById(R.id.img_chi_tiet);

                        tv_name.setText(name);
                        tv_gia.setText(gia);
                        Picasso.get().load(images).into(img_name);

                    }
                });

            }

            @Override
            public void onFailure(Call<ModelHanghoa> call, Throwable t) {
                Log.e(TAG, "onFailureasfsdfsdfds: "+ t.getMessage());
            }
        });
        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);
        btnSoluong = findViewById(R.id.soluong);
        tv_danhgia = findViewById(R.id.tv_danhgia);
        String tam1 = String.valueOf(btnSoluong.getText());
        tam =Integer.parseInt(tam1);
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tam ++;
                btnSoluong.setText(String.valueOf(tam));
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tam <= 1){

                }
                else {
                    tam --;
                    btnSoluong.setText(String.valueOf(tam));
                }
            }
        });
        tv_danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanhGiaActivity.class);
                startActivity(intent);
            }
        });
    }
}