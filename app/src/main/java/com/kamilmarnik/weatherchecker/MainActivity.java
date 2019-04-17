package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String CITY_NAME = "CITY_NAME";
    private Button checkWeatherBtn;
    private EditText cityNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkWeatherBtn = findViewById(R.id.checkWeatherBtn);
        cityNameText = findViewById(R.id.cityNameText);
    }

    public void checkingWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        String cityName = getCityName();
        intent.putExtra(CITY_NAME, cityName);
        startActivity(intent);
    }

    public String getCityName(){
        return cityNameText.getText().toString();
    }
}
