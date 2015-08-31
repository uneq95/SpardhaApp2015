package com.ritesh.spardha.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

import java.sql.SQLException;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class Event_Detail extends Activity {

    TextView sportsname,winner,runnerup,contact,rulebook;
    String[] details;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_layout);

        initialize();
        Bundle b= getIntent().getExtras();
       final long req_no = b.getInt("req_no");
         final int url_no = b.getInt("req_no")-1;
        Toast.makeText(this,"req no is "+req_no,Toast.LENGTH_LONG).show();

        EventsDb eventsDb = new EventsDb(this);
        try {
           details = eventsDb.GetSportDetail(req_no);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sportsname.setText(details[0]);
        winner.setText("Winner: "+details[1]);

        winner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (req_no == 1) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 18);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);

                }
                if (req_no == 14) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 19);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }

            }
        });


        runnerup.setText("Runner Up: " + details[2]);
        runnerup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (req_no == 1) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 18);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);

                }
                if (req_no == 14) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 19);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }

            }
        });
        contact.setText(details[4]);
        rulebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(req_no==4 || req_no==5 || req_no==18){
                    Toast.makeText(getBaseContext(),"Will be Uploaded Soon", Toast.LENGTH_LONG).show();
                }else {
                    bundle = new Bundle();
                    bundle.putInt("url_no", url_no);
                    Intent i  = new Intent(Event_Detail.this,TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });

    }

    private void initialize() {
        contact = (TextView) findViewById(R.id.tvcontact);
        sportsname = (TextView) findViewById(R.id.tvsportsname);
        winner = (TextView) findViewById(R.id.tvwinner);
        runnerup = (TextView) findViewById(R.id.tvrunnerup);
        rulebook = (TextView) findViewById(R.id.tvrulebook);

    }


}
