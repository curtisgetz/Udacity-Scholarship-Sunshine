/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.Trigger;

public class SunshineFirebaseJobService extends JobService {

    private static final String TAG = SunshineFirebaseJobService.class.getSimpleName();

    private AsyncTask<Void, Void, Void> mFetchWeatherTask;

    @Override
    public boolean onStartJob(final JobParameters job) {

        mFetchWeatherTask  = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Context context = getApplicationContext();
                SunshineSyncTask.syncWeather(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(job,false);
            }
        };
        mFetchWeatherTask.execute();
        Log.v(TAG, "Firebase OnStartJob ");
        return true;
    }


    @Override
    public boolean onStopJob(JobParameters job) {
        if (mFetchWeatherTask != null) mFetchWeatherTask.cancel(true);
        return true;
    }
}
//  (2) Make sure you've imported the jobdispatcher.JobService, not job.JobService
//  (3) Add a class called SunshineFirebaseJobService that extends jobdispatcher.JobService
//    (4) Declare an ASyncTask field called mFetchWeatherTask

//   (5) Override onStartJob and within it, spawn off a separate ASyncTask to sync weather data
//               (6) Once the weather data is sync'd, call jobFinished with the appropriate arguments

//   (7) Override onStopJob, cancel the ASyncTask if it's not null and return true
