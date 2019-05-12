package com.kamilmarnik.weatherchecker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWeather {
    @SerializedName("weather")
    @Expose
    List<Weather> weather = null;

    @SerializedName("main")
    @Expose
    Main main;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
