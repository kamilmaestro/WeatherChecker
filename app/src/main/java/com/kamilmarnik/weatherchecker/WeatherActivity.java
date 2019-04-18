package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WeatherActivity extends AppCompatActivity {

    private TextView mCityNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityNameText = findViewById(R.id.cityNameText);
        setCityName(getMyIntent());
    }

    public void setCityName(String cityName){
        mCityNameText.setText(cityName);
    }

    public String getMyIntent(){
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.CITY_NAME);
    }
}
