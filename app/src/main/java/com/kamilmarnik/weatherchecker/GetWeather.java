package com.kamilmarnik.weatherchecker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWeather {
    @SerializedName("main")
    @Expose
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
