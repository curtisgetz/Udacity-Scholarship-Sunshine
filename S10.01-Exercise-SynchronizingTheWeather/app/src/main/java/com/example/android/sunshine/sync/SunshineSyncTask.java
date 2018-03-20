package com.example.android.sunshine.sync;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

//    (1) Create a class called SunshineSyncTask

public class SunshineSyncTask  {

//    (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather

    synchronized public static void syncWeather(Context context) {

        try {
//        (3) Within syncWeather, fetch new weather data

            URL weatherUrl = NetworkUtils.getUrl(context);
            String jsonHttpResponse = NetworkUtils.getResponseFromHttpUrl(weatherUrl);

            ContentValues[] weatherContentValues = OpenWeatherJsonUtils.
                    getWeatherContentValuesFromJson(context, jsonHttpResponse);

            if(weatherContentValues != null && weatherContentValues.length > 0){
                ContentResolver sunshineResolver = context.getContentResolver();

                //       (4) If we have valid results, delete the old data and insert the new
                //Delete old weather data if we get new data.
                sunshineResolver.delete(WeatherContract.WeatherEntry.CONTENT_URI,
                         null, null);

                //add new weather data
                sunshineResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherContentValues);

            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }


}