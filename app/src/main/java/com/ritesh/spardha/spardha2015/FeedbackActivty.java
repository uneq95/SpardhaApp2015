package com.ritesh.spardha.spardha2015;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;

/**
 * Created by nikhil on 8/16/2015.
 */
public class FeedbackActivty extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button send;
    EditText suggestion;
    String emailadd[] = { "abhishek.ramakant.che14@iitbhu.ac.in", "ritesh.kumar.ece13@iitbhu.ac.in","publicity.spardha@iitbhu.ac.in"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedbacklayout);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)){
            new GCMStarter(this).GCMEnable();
        }
        send = (Button) findViewById(R.id.bsendsuggestion);
        suggestion = (EditText) findViewById(R.id.etsuggestion);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        send.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String suggestiontext = suggestion.getText().toString();
        Intent feedbackintent = new Intent(Intent.ACTION_SEND);
        feedbackintent.putExtra(Intent.EXTRA_EMAIL,emailadd);
        feedbackintent.putExtra(Intent.EXTRA_SUBJECT, "Spardha App Feedback");
        feedbackintent.setType("plain/txt");
        feedbackintent.putExtra(Intent.EXTRA_TEXT, suggestiontext);
        startActivity(feedbackintent);


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
