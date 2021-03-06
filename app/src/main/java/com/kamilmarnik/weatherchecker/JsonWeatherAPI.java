package com.kamilmarnik.weatherchecker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonWeatherAPI {
    @GET("/data/2.5/weather")
    Call<JSON> getWeather(@Query("q") String cityName, @Query("appid") String apiId, @Query("units") String units);
}
