package com.ritesh.spardha.spardha2015;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ritesh.spardha.gcm.QuickstartPreferences;
import com.ritesh.spardha.gcm.RegistrationIntentService;

/**
 * Created by ritesh_kumar on 16-Aug-15.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    int SPLASH_TIME_OUT = 2000;
    private static final String TAG = "SplashActivity";


    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!isNetworkConnected() && !sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)) {
            //showDialog();
            Toast.makeText(getApplicationContext(), "Please restart the app with Internet Connection to enable live notifications!", Toast.LENGTH_LONG).show();

        }

       /* if (isNetworkConnected() &&!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)) {
            System.out.println("inside broadcast receiver");


            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity
                            startActivity(new Intent(SplashActivity.this, SpardhaHomeTest.class));

                            // close this activity
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
            };
        }else{*/
            System.out.println("else conditiojn broadcast receive");
            mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    startActivity(new Intent(SplashActivity.this, SpardhaHomeTest.class));

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        //}
        if (checkPlayServices() && !sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null);

    }

}

