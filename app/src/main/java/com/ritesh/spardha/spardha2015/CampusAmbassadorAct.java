package com.ritesh.spardha.spardha2015;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ritesh.spardha.custom_views.ClearableEditText;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritesh_kumar on 02-Oct-15.
 */
public class CampusAmbassadorAct extends AppCompatActivity {
    Toolbar toolbar;
    String fullName, email, college, city, contactNum;
    ClearableEditText etName, etCollege, etCity, etNumber, etEmail;
    Button btReg;
    ProgressDialog pg;
    boolean regDataSentToserver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_ambassador);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)) {
            new GCMStarter(this).GCMEnable();
        }
        initViews();
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void initViews() {
        etName = (ClearableEditText) findViewById(R.id.etFullName);
        etEmail = (ClearableEditText) findViewById(R.id.etEmailId);
        etCollege = (ClearableEditText) findViewById(R.id.etCollege);
        etCity = (ClearableEditText) findViewById(R.id.etCity);
        etNumber = (ClearableEditText) findViewById(R.id.etContactNumber);
        btReg = (Button) findViewById(R.id.btRegister);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void register() {
        if (etName.getText().length() > 0) {
            if (etEmail.getText().length() > 0) {
                if (etCollege.getText().length() > 0) {
                    if (etCity.getText().length() > 0) {

                        if (etNumber.getText().length() == 10) {
                            dataCheck();
                        } else {
                            etNumber.setError("Invalid Mobile Number!");
                            etNumber.requestFocus();
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
            etName.setError("Enter your Name!");
            etName.requestFocus();
        }
    }

    private void dataCheck() {
        fullName = etName.getText().toString();
        email = etEmail.getText().toString();
        college = etCollege.getText().toString();
        city = etCity.getText().toString();
        contactNum = etNumber.getText().toString();
        pg = new ProgressDialog(this);
        if (isNetworkConnected()) {
            new RegisterUserAsyncTask().execute();
        }

    }

    boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null);

    }

    class RegisterUserAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!pg.isShowing()) {
                pg.show();
                pg.setCancelable(false);
                pg.setMessage("Registering ... Please wait!");
                pg.show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<NameValuePair> nameValuePairs;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://spardha.co.in/campus_amb_android.php");
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
                        Toast.makeText(CampusAmbassadorAct.this, "Connection Error! Please try again!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CampusAmbassadorAct.this, "You are successfully registered for Spardha'15", Toast.LENGTH_LONG).show();
                    clearData();
                }


            }
        }
    }

    private void clearData() {
        etName.setText("");
        etEmail.setText("");
        etCollege.setText("");
        etCity.setText("");
        etNumber.setText("");
    }

    private ArrayList<NameValuePair> regDataEncoder() {
        ArrayList<NameValuePair> regData = new ArrayList<>();
        regData.add(new BasicNameValuePair("name", fullName));
        regData.add(new BasicNameValuePair("email", email));
        regData.add(new BasicNameValuePair("college", college));
        regData.add(new BasicNameValuePair("city", city));
        regData.add(new BasicNameValuePair("mobile", contactNum));
        return regData;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
