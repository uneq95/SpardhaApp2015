package com.ritesh.spardha.spardha2015;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by ritesh_kumar on 06-Oct-15.
 */
public class SettingsActivity extends PreferenceActivity {//implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    /*@Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences pref;
        switch(key){

            case "1":pref = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor edit =pref.edit();
                            edit.putBoolean(fales);break;

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }*/
}
