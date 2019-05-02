package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String CITY_NAME = "CITY_NAME", SPACE = " ", ACCEPTABLE_CHARS = "[^\\W\\d]*",
            EMPTY_STRING = "", DEFAULT_VAL = "Default";
    public static final int MAX_CHARS = 30;
    private Button mCheckWeatherBtn;
    private EditText mCityNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheckWeatherBtn = findViewById(R.id.checkWeatherBtn);
        mCityNameText = findViewById(R.id.cityNameText);
        setMaxChars(MAX_CHARS);
        setRightContent();
        loadData();
    }

    public void setMaxChars(final int MAX_CHARS){
        mCityNameText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(MAX_CHARS)
        });
    }

    public void setRightContent(){
        mCityNameText.setFilters(new InputFilter[]{
           new InputFilter() {
               @Override
               public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                   if(source.equals(SPACE))
                       return source;
                   else if(source.toString().matches(ACCEPTABLE_CHARS))
                       return source;
                   else
                       return EMPTY_STRING;
               }
           }
        });
    }

    public void checkingWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        String cityName = getCityName();
        saveData(cityName);
        intent.putExtra(CITY_NAME, cityName);
        startActivity(intent);
    }

    public String getCityName(){
        return mCityNameText.getText().toString();
    }

    public void saveData(String cityName){
        SharedPreferences savedCityName = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = savedCityName.edit();
        editor.putString(CITY_NAME, cityName);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String cityName = sharedPreferences.getString(CITY_NAME, DEFAULT_VAL);
        mCityNameText.setText(cityName);
    }
}
