package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ritesh_kumar on 11-Aug-15.
 */
public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageButton fbRitesh, linkedInRitesh, gPlusRitesh, fbAbhishek, linkedInAbhishek, gPlusAbhishek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        initViews();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initViews() {
        fbRitesh = (ImageButton) findViewById(R.id.fbRitesh);
        gPlusRitesh = (ImageButton) findViewById(R.id.gplusRitesh);
        linkedInRitesh = (ImageButton) findViewById(R.id.inRitesh);
        fbAbhishek = (ImageButton) findViewById(R.id.fbAbhishek);
        gPlusAbhishek = (ImageButton) findViewById(R.id.gplusAbhishek);
        linkedInAbhishek = (ImageButton) findViewById(R.id.inAbhishek);
        fbRitesh.setOnClickListener(this);
        gPlusRitesh.setOnClickListener(this);
        linkedInRitesh.setOnClickListener(this);
        fbAbhishek.setOnClickListener(this);
        gPlusAbhishek.setOnClickListener(this);
        linkedInAbhishek.setOnClickListener(this);

    }

    public static Intent getOpenFacebookIntent(Context context, String username) {

//        try {
//            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("fb://profile/%s", username)));
//        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://www.facebook.com/%s", username)));
//        }
    }

    private void openGPlusIntent(String userId) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        try{
//            intent.setClassName("com.google.android.apps.plus",
//                    "com.google.android.apps.plus.phone.UrlGatewayActivity");
//        }catch(Exception e){
            intent = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://plus.google.com/%s",userId)));
       // }

        intent.putExtra("customAppUri", userId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void openLinkedIn(String userId) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://in.linkedin.com/in/" + userId)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbRitesh:
                startActivity(getOpenFacebookIntent(this, "uneq95").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.gplusRitesh:
                openGPlusIntent("102953458218354109367");
                break;
            case R.id.inRitesh:
                openLinkedIn("riteshkumarece13iitbhu");
                break;
            case R.id.fbAbhishek:startActivity(getOpenFacebookIntent(this, "abhishek.pandey.100483").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.gplusAbhishek:openGPlusIntent("113192039843114411696");
                break;
            case R.id.inAbhishek:openLinkedIn("/pub/abhishek-pandey/8a/74/513");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
