package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    public static final String POLAND_GMT = "GMT+1", APP_ID = "749561a315b14523a8f5f1ef95e45864", UNITS = "metric", ERROR = "Error has occured";
    private String cityName;
    private TextView mCityNameText, mHourText, mTempText, mPressText, mHumText, mTempMinText, mTempMaxText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityNameText = findViewById(R.id.cityNameText);
        mHourText = findViewById(R.id.hourText);
        mTempText = findViewById(R.id.tempSetText);
        mPressText = findViewById(R.id.pressSetText);
        mHumText = findViewById(R.id.humSetText);
        mTempMinText = findViewById(R.id.minTempSetText);
        mTempMaxText = findViewById(R.id.maxTempSetText);

        setCityName(getMyIntent());
        mHourText.setText(getTime(POLAND_GMT));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonWeatherAPI jsonWeatherAPI = retrofit.create(JsonWeatherAPI.class);
        Call<GetWeather> call = jsonWeatherAPI.getWeather(getCityName(), APP_ID, UNITS);
        call.enqueue(new Callback<GetWeather>() {
            @Override
            public void onResponse(Call<GetWeather> call, Response<GetWeather> response) {

            }

            @Override
            public void onFailure(Call<GetWeather> call, Throwable t) {
                mCityNameText.setText(ERROR);
                Toast.makeText(WeatherActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
        mCityNameText.setText(cityName);
    }

    public String getMyIntent(){
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.CITY_NAME);
    }

    public String getCityName(){
        return this.cityName;
    }

    public String getTime(final String GMT ){
        TimeZone timeZone =TimeZone.getTimeZone(GMT);
        Calendar cal = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(cal.getTime());
    }
}
