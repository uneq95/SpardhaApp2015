package com.ritesh.spardha.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ritesh.spardha.adapters.CategoriesGridAdapter;
import com.ritesh.spardha.spardha2015.R;
import com.ritesh.spardha.spardha2015.SponsersActivity;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class Event_List extends AppCompatActivity{

    GridView gridView;
    CategoriesGridAdapter adapter;
    Toolbar toolbar;
    Bundle b;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_grid_sports);
        String[] categories={"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","FootBall","HandBall","Hockey","Kabaddi","KhoKho","Squash","TaeKwonDo","Tennis","TableTennis","VolleyBall","WeightLifting"};
        int[] resId={R.drawable.athletics,R.drawable.badminton,R.drawable.basket_ball,R.drawable.boxing,R.drawable.carrom,R.drawable.chess,R.drawable.cricket,R.drawable.football,R.drawable.handball,R.drawable.hockey,R.drawable.kabaddi,R.drawable.kho_kho,R.drawable.squash,R.drawable.tae_kwon_do,R.drawable.tennis,R.drawable.tt,R.drawable.volley_ball,R.drawable.weight_lifting};
        gridView = (GridView) findViewById(R.id.gridViewSportsCategory);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        adapter= new CategoriesGridAdapter(this.getBaseContext(),categories,resId);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                b = new Bundle();
                b.putInt("req_no",position+1);
                intent = new Intent(Event_List.this, Event_Detail.class);
                intent.putExtras(b);
                startActivity(intent);
            }


        });

    }
}
