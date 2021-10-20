package com.example.mydome.mvvmdome.request;



import com.example.mydome.mvvmdome.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cyc on 18/5/5.
 */

public interface QueryWeatherRequest {

    @GET("data/cityinfo/101210101.html")
    Call<WeatherData> queryWeather();

    @POST("data/cityinfo/101210101.html")
    Call<WeatherData> postqueryWeather();

}
