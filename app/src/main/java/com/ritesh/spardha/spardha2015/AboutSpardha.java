package com.ritesh.spardha.spardha2015;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;

/**
 * Created by ritesh_kumar on 12-Sep-15.
 */
public class AboutSpardha extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutspardha2);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)){
            new GCMStarter(this).GCMEnable();
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        WebView mWebView = (WebView) findViewById(R.id.webView2);

               String text = "<html><body>"
                        + "<p align=\"justify\">"
                        + getString(R.string.about_spardha)
                         + "</p> "
                         + "</body></html>";

                 mWebView.loadData(text, "text/html", "utf-8");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
