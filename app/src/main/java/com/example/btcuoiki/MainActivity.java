package com.example.btcuoiki;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Context context = MainActivity.this;
    private ArrayList<String> name;
    private ArrayList<String> gia;
    private ArrayList<Integer> id;
    private ArrayList<String> images;
    private MainAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private Spinner spn_sap_xep, spn_danh_muc, spn_gio_hang;
    private TextView tv;
    private SpinnerAdapter spinnerAdapter;
    private DrawerLayout mDrawerLayout;

    private List<ModelHanghoa> list = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        spn_sap_xep = findViewById(R.id.spn_sap_xep);
        spn_danh_muc = findViewById(R.id.spn_danh_muc);
        spn_gio_hang = findViewById(R.id.spn_gio_hang);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        tv = findViewById(R.id.tv_hien_thi);

        name = new ArrayList<String>();
        gia = new ArrayList<String>();
        images = new ArrayList<String>();
        id = new ArrayList<Integer>();

        Methods methods = RetrofitClient.getRetrofitHangHoa().create(Methods.class);
        Call<List<ModelHanghoa>> call = methods.getAllDataHangHoa();

        call.enqueue(new Callback<List<ModelHanghoa>>() {
            @Override
            public void onResponse(Call<List<ModelHanghoa>> call, Response<List<ModelHanghoa>> response) {
                list = response.body();
                Log.e(TAG, "onResponse: " + list );

                if (list == null) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(ModelHanghoa data1: list){
                            name.add(data1.getTen());
                            gia.add(data1.getGia());
                            images.add(data1.getHinhanh());
                            id.add(data1.getId());
                        }
                        adapter = new MainAdapter(name, gia, images, id, context);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<ModelHanghoa>> call, Throwable t) {
                Log.e(TAG, "onFailureasfsdfsdfds: "+ t.getMessage());
            }
        });


        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


        //spinner_sort
        spinnerAdapter = new SpinnerAdapter(this, R.layout.item_selected, getListCategory());
        spn_sap_xep.setAdapter(spinnerAdapter);
        spn_sap_xep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, spinnerAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAdapter = new SpinnerAdapter(this, R.layout.item_selected, getListDanhMuc());
        spn_danh_muc.setAdapter(spinnerAdapter);
        spn_danh_muc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, spinnerAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAdapter = new SpinnerAdapter(this, R.layout.item_selected, getListGioHang());
        spn_gio_hang.setAdapter(spinnerAdapter);
        spn_gio_hang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, spinnerAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private List<SpinnerSort> getListCategory() {
        List<SpinnerSort> list = new ArrayList<>();
        list.add(new SpinnerSort("Sắp xếp mặc định"));
        list.add(new SpinnerSort("Giá thấp tới cao"));
        list.add(new SpinnerSort("Giá cao tới thấp"));
        list.add(new SpinnerSort("Sản phẩm hot"));
        return list;
    }

    private List<SpinnerSort> getListDanhMuc() {
        List<SpinnerSort> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new SpinnerSort("Rau củ"));
        listDanhMuc.add(new SpinnerSort("Hải sản"));
        listDanhMuc.add(new SpinnerSort("Trái cây"));
        listDanhMuc.add(new SpinnerSort("Đồ uống"));
        listDanhMuc.add(new SpinnerSort("Đồ khô"));
        listDanhMuc.add(new SpinnerSort("Thịt trứng"));
        return listDanhMuc;
    }

    private List<SpinnerSort> getListGioHang() {
        List<SpinnerSort> listGioHang = new ArrayList<>();
        listGioHang.add(new SpinnerSort("Cà chua Đà Lạt"));
        listGioHang.add(new SpinnerSort("Bắp Mỹ"));
        listGioHang.add(new SpinnerSort("Chuối vàng"));
        return listGioHang;
    }
}