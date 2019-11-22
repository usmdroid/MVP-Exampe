package com.tomreaddle.mvpexample.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {


    String BASE_URL = "https://usmano8102.000webhostapp.com/";

    @GET("demos/mvp/access.json")
    Call<APIModel> getData();
}
