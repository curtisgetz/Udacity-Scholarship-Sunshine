package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

/**
 * Created by curti on 1/31/2018.
 */

public class SettingsFragmentTwo extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener{




    // Do steps 5 - 11 within SettingsFragment
    //  (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    //  (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets
    // the summary of the preference
    private void setPreferenceSummary(Preference preference, Object object){
        String value = object.toString();
        if(preference instanceof ListPreference){
            //For List preference, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if(prefIndex >= 0){
                //set summary text
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }else {
            //String value = object.toString();
            preference.setSummary(value);
        }

    }
    //   (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        // GO through all preferences and set up their summary
        for(int i = 0;i < count; i++ ) {
            Preference preference = prefScreen.getPreference(i);
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);

            }
        }
        // Do step 9 within onCreatePreference
        //  (9) Set the preference summary on each preference that isn't a CheckBoxPreference

        //   (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

        //   (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart

        //   (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed

    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Figure out which preference was changed
        Preference preference = findPreference(key);
        if (null != preference){
            // Updates the summary for the preference
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }

    }
}
