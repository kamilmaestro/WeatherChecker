package com.kamilmarnik.weatherchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class WeatherActivity extends AppCompatActivity {

    public static final String POLAND_GMT = "GMT+1";
    private TextView mCityNameText;
    private TextView mHourText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityNameText = findViewById(R.id.cityNameText);
        mHourText = findViewById(R.id.hourText);
        setCityName(getMyIntent());
        mHourText.setText(getTime(POLAND_GMT));
    }

    public void setCityName(String cityName){
        mCityNameText.setText(cityName);
    }

    public String getMyIntent(){
        Intent intent = getIntent();
        return intent.getStringExtra(MainActivity.CITY_NAME);
    }

    public String getTime(final String GMT ){
        TimeZone timeZone =TimeZone.getTimeZone(GMT);
        Calendar cal = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(cal.getTime());
    }
}
