package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private static TextView mdaysWeatherView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mdaysWeatherView = (TextView) findViewById(R.id.tv_days_weather);
        //   (2) Display the weather forecast that was passed from MainActivity
        Intent callingIntent = getIntent();
        if(callingIntent.hasExtra(Intent.EXTRA_TEXT)){
            String incomingText = callingIntent.getStringExtra(Intent.EXTRA_TEXT);
            mdaysWeatherView.setText(incomingText);
        }

    }
}