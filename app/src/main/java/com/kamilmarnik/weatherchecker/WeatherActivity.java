package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    public static final String POLAND_GMT = "GMT+1", APP_ID = "749561a315b14523a8f5f1ef95e45864",
            UNITS = "metric", ERROR = "Error has occurred", CELSIUS = "\u2103", HECTOPASCAL = "hPa", PERCENT = "\u0025";
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

        openURL();
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

    public void openURL(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonWeatherAPI jsonWeatherAPI = retrofit.create(JsonWeatherAPI.class);
        sendRequest(jsonWeatherAPI);
    }

    public void sendRequest(JsonWeatherAPI jsonWeatherAPI){
        Call<GetWeather> call = jsonWeatherAPI.getWeather(getCityName().concat(",pl"), APP_ID, UNITS);
        call.enqueue(new Callback<GetWeather>() {
            @Override
            public void onResponse(Call<GetWeather> call, Response<GetWeather> response) {
                if(!response.isSuccessful()){
                    mCityNameText.setText(ERROR);
                    Toast.makeText(WeatherActivity.this, "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                fillTextViews(response);
            }

            @Override
            public void onFailure(Call<GetWeather> call, Throwable t) {
                mCityNameText.setText(ERROR);
                Toast.makeText(WeatherActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fillTextViews(Response<GetWeather> response){
        GetWeather getWeather = response.body();
        Main main = Objects.requireNonNull(getWeather).getMain();

        mTempText.setText(String.format(Locale.getDefault(), "%.2f ", main.getTemp()).concat(CELSIUS));
        mPressText.setText(String.format(Locale.getDefault(), "%d ", main.getPressure()).concat(HECTOPASCAL));
        mHumText.setText(String.format(Locale.getDefault(), "%d ", main.getHumidity()).concat(PERCENT));
        mTempMinText.setText(String.format(Locale.getDefault(), "%.2f ", main.getTempMin()).concat(CELSIUS));
        mTempMaxText.setText(String.format(Locale.getDefault(), "%.2f ", main.getTempMax()).concat(CELSIUS));
    }
}
