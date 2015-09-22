package com.ritesh.spardha.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ritesh.spardha.adapters.CattestAdapter;
import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.spardha2015.R;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class Event_List extends AppCompatActivity{

    GridView gridView;
    Toolbar toolbar;
    Bundle b;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_test);

        final String[] categories={"Athletics","Badminton","BasketBall","Boxing","Carrom","Chess","Cricket","Football","HandBall","Hockey","Kabaddi","KhoKho","Power Lifting","Squash","Taekwondo","Tennis","Table Tennis","Volleyball","Weight Lifting"};
/*
        int[] resId={R.drawable.athletics,R.drawable.badminton,R.drawable.basketball,R.drawable.boxing,R.drawable.carrom,R.drawable.chess,R.drawable.cricket,R.drawable.football,R.drawable.handball,R.drawable.hockey,R.drawable.kabaddi,R.drawable.khokho,R.drawable.squash,R.drawable.taekwondo,R.drawable.tennis,R.drawable.tt,R.drawable.volleyball,R.drawable.weightlifting};
*/
        int[] resId={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,R.drawable.s15,R.drawable.s16,R.drawable.s17,R.drawable.s18,R.drawable.s19};
        String[] colorCodes ={"#e74c3c","#3498db","#e67e22","#9b59b6","#27ae60","#2c3e50","#e74c3c","#3498db","#e67e22","#9b59b6","#27ae60","#2c3e50","#e74c3c","#3498db","#e67e22","#9b59b6","#27ae60","#2c3e50","#e74c3c"};
        gridView = (GridView) findViewById(R.id.gvcattest);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        new GCMStarter(this).GCMEnable();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(10);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Events");
        }
        CattestAdapter adapter= new CattestAdapter(this.getBaseContext(),categories,resId,colorCodes);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                b = new Bundle();
                b.putInt("req_no",position+1);
                intent = new Intent(Event_List.this, Event_Detail.class);
                intent.putExtras(b);
                intent.putExtra("sportTitle",categories[position]);
                startActivity(intent);
            }


        });

    }
}
