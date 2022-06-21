package com.example.btcuoiki;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Methods {
    @GET("/getapp")
    Call<List<ModelHanghoa>> getAllDataHangHoa();


    @GET("/getproduct?")
    Call<ModelHanghoa> getProduct(
            @Query("id") Integer id
    );

    @POST("/login")
    Call<Token> getToken(@Body UserToken userToken);

    @POST("/register")
    Call<Token> getSignin(@Body User user);
}
