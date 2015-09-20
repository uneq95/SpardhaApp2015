package com.ritesh.spardha.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 20-Sep-15.
 */
public class CattestActivity extends AppCompatActivity {

    String[] categories = {"ABOUT SPARDHA","INAUGRATION", "EVENTS", "INFORMALS","SPONSERS","GALLERY","YOUTUBE","TESTIMONIALS"};
    int[] resId = {R.drawable.x6,R.drawable.x4, R.drawable.x8, R.drawable.x3,R.drawable.x2,R.drawable.x5, R.drawable.x7, R.drawable.x1};
    String[] colorCodes ={"#e74c3c","#3498db","#e67e22","#9b59b6","#27ae60","#e74c3c","#2c3e50","#3498db"};
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_test);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Categories");
        }
        CattestAdapter adapter = new CattestAdapter(this, categories, resId,colorCodes);
        GridView gv  =(GridView)findViewById(R.id.gvcattest);
        gv.setAdapter(adapter);
    }
}
