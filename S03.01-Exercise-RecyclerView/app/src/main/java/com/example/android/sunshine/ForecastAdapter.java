package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by curti on 1/25/2018.
 */
//
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{


    //private int weatherDataCount;
    private String[] mWeatherData;



    // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////
    //   (15) Add a class file called ForecastAdapter
    //   (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>

    //   (23) Create a private string array called mWeatherData

    //   (47) Create the default constructor (we will pass in parameters in a later lesson)
    public ForecastAdapter(){

    }
    //   (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
    //   (17) Extend RecyclerView.ViewHolder

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mWeatherTextView;

        public ForecastAdapterViewHolder(View view){
            super(view);
            mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);
        }

    }
    // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////
    //   (18) Create a public final TextView variable called mWeatherTextView

    //   (19) Create a constructor for this class that accepts a View as a parameter
    //   (20) Call super(view) within the constructor for ForecastAdapterViewHolder
    //   (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
    // Within ForecastAdapterViewHolder ///////////////////////////////////////////////////////////

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        boolean attachToParentImmedietly =  false;
        int listItemLayOut = R.layout.forcast_list_item;
        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(listItemLayOut, parent, attachToParentImmedietly);
        return new ForecastAdapterViewHolder(view);


    }


    //   (24) Override onCreateViewHolder
    //   (25) Within onCreateViewHolder, inflate the list item xml into a view
    //   (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
       String weatherForThisDay = mWeatherData[position];
       holder.mWeatherTextView.setText(weatherForThisDay);
       // super.onBindViewHolder(holder, position, payloads);

    }


    //   (27) Override onBindViewHolder
    //   (28) Set the text of the TextView to the weather for this list item's position

    @Override
    public int getItemCount() {
        if(mWeatherData == null){
            return 0;
        }
        //int weatherDataCount = mWeatherData.
        return mWeatherData.length;
    }

    //   (29) Override getItemCount
    //   (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null

    public void setWeatherData(String[] weatherData) {
       mWeatherData = weatherData;
       notifyDataSetChanged();

    }
    //   (31) Create a setWeatherData method that saves the weatherData to mWeatherData
    //   (32) After you save mWeatherData, call notifyDataSetChanged
    // Within ForecastAdapter.java /////////////////////////////////////////////////////////////////




}
