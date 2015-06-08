package com.ritesh.spardha.CustomizeGallery;

/**
 * Created by Abhishek on 06-06-2015.
 */
import android.app.Application;
import android.content.Context;

public class CustomGallery extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}