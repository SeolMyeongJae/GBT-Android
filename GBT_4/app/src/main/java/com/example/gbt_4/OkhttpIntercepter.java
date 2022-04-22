package com.example.gbt_4;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OkhttpIntercepter{

    @GET("/users/{user}/repos")
    Call<List<JsonObject>> getMyRepos(@Path("user") String userName);

}
