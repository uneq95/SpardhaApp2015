package com.ritesh.spardha.spardha2015;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritesh_kumar on 06-Aug-15.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Toolbar toolbar;
    EditText etFullName, etEmail, etCollege, etCity, etContactNo, etDesignation;
    Button btMaleSelections, btFemaleSelections, btregister;
    String fullName, email, college, city, contactNum, designation;
    ArrayList<String> selectedSports;
    ArrayAdapter<String> adapter;
    String maleOrFemale = "male";
    ProgressDialog pg;
    boolean regDataSentToserver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();

        setContentView(R.layout.registration_layout_new);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)){
            new GCMStarter(this).GCMEnable();
        }
        initView();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initView() {
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmailId);
        etCollege = (EditText) findViewById(R.id.etCollege);
        etCity = (EditText) findViewById(R.id.etCity);
        etContactNo = (EditText) findViewById(R.id.etContactNumber);
        etDesignation = (EditText) findViewById(R.id.etDesignation);
        btMaleSelections = (Button) findViewById(R.id.btMaleSelection);
        btFemaleSelections = (Button) findViewById(R.id.btFemaleSelection);
        btregister = (Button) findViewById(R.id.btRegister);
        btMaleSelections.setOnClickListener(this);
        btFemaleSelections.setOnClickListener(this);
        btregister.setOnClickListener(this);
        pg = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btMaleSelection:
                maleOrFemale = "male";
                showAlertDialog(R.array.male_sports);
                break;
            case R.id.btFemaleSelection:
                maleOrFemale = "female";
                showAlertDialog(R.array.female_sports);
                break;
            case R.id.btRegister:
                reg();
                break;


        }


    }

    private void showAlertDialog(int sportsArrayId) {
        selectedSports = new ArrayList<>();
        final String[] sports = this.getResources().getStringArray(sportsArrayId);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select sports");
        builder.setMultiChoiceItems(sports, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {
                    selectedSports.add(sports[which]);
                } else if (selectedSports.contains(sports[which])) {
                    selectedSports.remove(sports[which]);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Display selected sports
            }
        });
        builder.create();
        builder.show();
    }

    private void reg() {
        if (etFullName.getText().length() > 0) {
            if (etEmail.getText().length() > 0) {
                if (etCollege.getText().length() > 0) {
                    if (etCity.getText().length() > 0) {

                        if (etContactNo.getText().length() == 10) {
                            eventCheck();
                        } else {
                            etContactNo.setError("Invalid Mobile Number!");
                            etContactNo.requestFocus();
                        }

                    } else {
                        etCity.setError("Enter your City!");
                        etCity.requestFocus();
                    }
                } else {
                    etCollege.setError("Enter your college Name!");
                    etCollege.requestFocus();
                }
            } else {
                etEmail.setError("Enter your Email-id!");
                etEmail.requestFocus();
            }
        } else {
            etFullName.setError("Enter your Name!");
            etFullName.requestFocus();
        }
    }

    private void eventCheck() {
        adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_list_item_1);
        if (selectedSports != null) {
            for (int i = 0; i < selectedSports.size(); i++) {
                adapter.add(selectedSports.get(i));
            }
            register();
        } else {
            Toast.makeText(this, "Please select at least one sport!", Toast.LENGTH_LONG).show();
        }
    }

    public void register() {
        fullName = etFullName.getText().toString();
        email = etEmail.getText().toString();
        college = etCollege.getText().toString();
        city = etCity.getText().toString();
        designation = etDesignation.getText().toString();
        contactNum = etContactNo.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(isNetworkConnected()){
                    new RegisterUserAsyncTask().execute();
                }else{
                    Toast.makeText(getBaseContext(),"Please connect to the internet!",Toast.LENGTH_LONG).show();
                }


            }
        });
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.select_dialog_singlechoice);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("You are registering for the following sports:");
        builder.create();
        builder.show();

    }

    boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo()!=null);

    }
    class RegisterUserAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!pg.isShowing()) {
                pg.show();
                pg.setCancelable(false);
                pg.setMessage("Registering you for Spardha'15... Please wait!");
                pg.show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<NameValuePair> nameValuePairs;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://spardha.co.in/android_recoded_twice.php");
            try {
                nameValuePairs = regDataEncoder();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                regDataSentToserver = Integer.parseInt(response) == 200;

            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Connection Error! Please try again!", Toast.LENGTH_SHORT).show();
                        regDataSentToserver = false;
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pg.isShowing()) {

                pg.dismiss();
                if (regDataSentToserver) {
                    Toast.makeText(context, "You are successfully registered for Spardha'15", Toast.LENGTH_LONG).show();
                    clearData();
                }


            }
        }
    }

    private void clearData() {
        etFullName.setText("");
        etEmail.setText("");
        etCollege.setText("");
        etCity.setText("");
        etDesignation.setText("");
        etContactNo.setText("");
        selectedSports = null;
    }

    private ArrayList<NameValuePair> regDataEncoder() {
        ArrayList<NameValuePair> regData = new ArrayList<>();
        regData.add(new BasicNameValuePair("full_name", fullName));
        regData.add(new BasicNameValuePair("email", email));
        regData.add(new BasicNameValuePair("college", college));
        regData.add(new BasicNameValuePair("city", city));
        regData.add(new BasicNameValuePair("designation", designation));
        regData.add(new BasicNameValuePair("contact_num", contactNum));
        regData.add(new BasicNameValuePair("male_or_female", maleOrFemale));
        regData.add(new BasicNameValuePair("sports", generateSportsList()));
        return regData;
    }

    private String generateSportsList() {
        StringBuilder sb = new StringBuilder(selectedSports.get(0));
        String allSportList;
        for (int i = 1; i < selectedSports.size(); i++) {
            sb.append(String.format(" , %s", selectedSports.get(i)));
        }
        allSportList = sb.toString();
        return allSportList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
