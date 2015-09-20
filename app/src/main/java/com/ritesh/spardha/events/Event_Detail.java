package com.ritesh.spardha.events;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

/**
 * Created by Abhishek on 8/29/2015.
 */
public class Event_Detail extends AppCompatActivity {

    TextView winner, runnerup, contacta, contactb, rulebook;
    String[] details;
    Bundle bundle;
    CardView cardview, contact1_cardview, contact2_cardview;
    ImageView call_1, call_2;
    private ProgressDialog pDialog;
    String error;
    public static final int progress_bar_type = 0;
    int url_no;
    String address = "http://www.spardha.co.in/file/";
    String[] RuleBookUrl = {"athletics.pdf", "badminton.pdf", "basketball.pdf", "boxing.pdf", "carrom.pdf", "chess.pdf", "cricket.pdf", "football.pdf", "handball.pdf", "hockey.pdf",
            "kabaddi.pdf", "kho-kho.pdf", "squash.pdf", "tae-kwon-do.pdf", "tennis.pdf", "table-tennis.pdf", "volleyball.pdf", "lifting.pdf", "winner-athletics.pdf", "winner-tae-kwon-do.pdf"};

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_final_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        String activityTitle = getIntent().getStringExtra("sportTitle");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(activityTitle);
        }


        initialize();
        Bundle b = getIntent().getExtras();
        final long req_no = b.getInt("req_no");
        url_no = b.getInt("req_no") - 1;
        //Toast.makeText(this,"req no is "+req_no,Toast.LENGTH_LONG).show();

        EventsDb eventsDb = new EventsDb(this);
        try {
            details = eventsDb.GetSportDetail(req_no);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // sportsname.setText(details[0]);
        winner.setText(details[1]);

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
                if (req_no == 15) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 19);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }

            }
        });


        runnerup.setText(details[2]);
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
                if (req_no == 15) {
                    bundle = new Bundle();
                    bundle.putInt("url_no", 19);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }

            }
        });
        contacta.setText(details[4]);

        contact1_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (details[6].equals("---")) {
                    Toast.makeText(getBaseContext(), "Number not found", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(String.format("tel:%s", details[6])));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getBaseContext().startActivity(intent);
                }
            }
        });


        if (!details[5].equals("---")) {
            cardview.setVisibility(View.VISIBLE);
            contactb.setText(details[5]);
        }
        contact2_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (details[6].equals("---")) {
                    Toast.makeText(getBaseContext(), "Number not found", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getBaseContext(),details[7],Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(String.format("tel:%s", details[7])));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getBaseContext().startActivity(intent);

                }
            }
        });
        rulebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String final_url = address + RuleBookUrl[url_no];

                if (req_no == 4 || req_no == 5 || req_no == 13||req_no==19) {
                    Toast.makeText(getBaseContext(), "Will be Uploaded Soon", Toast.LENGTH_LONG).show();
                } else {


                    File filefile = new File(Environment.getExternalStorageDirectory() + "/" + RuleBookUrl[url_no]);
                    if (filefile.exists()) {
                        try {
                            final Uri uri = Uri.fromFile(filefile);
                            Intent intenturl = new Intent(Intent.ACTION_VIEW);
                            intenturl.setDataAndType(uri, "application/pdf");
                            intenturl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intenturl);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        new DownloadFileFromURL().execute(final_url);
                    }


                    // new DownloadFileFromURL().execute(file_url);
                    /*bundle = new Bundle();
                    bundle.putInt("url_no", url_no);
                    Intent i = new Intent(Event_Detail.this, TestView.class);
                    i.putExtras(bundle);
                    startActivity(i);*/
                }
            }
        });

    }

    private void initialize() {
        call_1 = (ImageView) findViewById(R.id.b_call_contact1);
        call_2 = (ImageView) findViewById(R.id.b_call_contact2);
        cardview = (CardView) findViewById(R.id.card_view_contact2);
        contacta = (TextView) findViewById(R.id.tvcontact);
        contactb = (TextView) findViewById(R.id.tvSportContactName2);
        //sportsname = (TextView) findViewById(R.id.tvsportsname);
        winner = (TextView) findViewById(R.id.tvwinner);
        runnerup = (TextView) findViewById(R.id.tvrunnerup);
        rulebook = (TextView) findViewById(R.id.tvrulebook);
        contact1_cardview = (CardView) findViewById(R.id.card_view_contact1);
        contact2_cardview = (CardView) findViewById(R.id.card_view_contact2);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream("/sdcard/" + RuleBookUrl[url_no]);

                byte data[] = new byte[1024];

                long total = 0;
                try {
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                        output.write(data, 0, count);
                    }

                    output.flush();
                } catch (Exception e) {
                    error = e.toString();

                }
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
            if (pDialog.isShowing()) {
                dismissDialog(progress_bar_type);
            }
            File filefile = new File(Environment.getExternalStorageDirectory() + "/" + RuleBookUrl[url_no]);
            final Uri uri = Uri.fromFile(filefile);
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/" + RuleBookUrl[url_no];

            if (filefile.exists()) {
                try {
                    Toast.makeText(getBaseContext(), "Downloaded to " + imagePath, Toast.LENGTH_LONG).show();
                    Intent intenturl = new Intent(Intent.ACTION_VIEW);
                    intenturl.setDataAndType(uri, "application/pdf");
                    intenturl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intenturl);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getBaseContext(), "Download Failed", Toast.LENGTH_LONG).show();
            }


        }

    }

}
