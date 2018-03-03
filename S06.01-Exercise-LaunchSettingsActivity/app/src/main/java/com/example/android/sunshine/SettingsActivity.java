package com.example.android.sunshine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by curti on 1/31/2018.
 */

public class SettingsActivity extends AppCompatActivity {
    //  (2) Set setDisplayHomeAsUpEnabled to true on the support ActionBar
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_settings);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ActionBar actionBar = this.getSupportActionBar();
  //      if(actionBar != null){
    //        actionBar.setDisplayHomeAsUpEnabled(true);
    //    }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
