package com.example.btcuoiki;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit;
    private static String BASE_URL = "https://5d9d-2402-9d80-34b-2928-701c-778b-ea0c-415f.ap.ngrok.io";
    public static Retrofit getRetrofitHangHoa() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
