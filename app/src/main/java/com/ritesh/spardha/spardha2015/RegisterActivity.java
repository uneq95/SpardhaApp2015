package com.ritesh.spardha.spardha2015;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 06-Aug-15.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Toolbar toolbar;
    EditText etFullName, etEmail, etCollege, etCity, etContactNo, etDesignation;
    Button btMaleSelections, btFemaleSelections, btregister;
    String fullName, email, college, city, contactNum, designation;
    ArrayList<NameValuePair> formData;
    ArrayList<String> selectedSports;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();
        setContentView(R.layout.registration_layout_new);
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
        etDesignation = (EditText) findViewById(R.id.etCity);
        btMaleSelections = (Button) findViewById(R.id.btMaleSelection);
        btFemaleSelections = (Button) findViewById(R.id.btFemaleSelection);
        btregister = (Button) findViewById(R.id.btRegister);
        btMaleSelections.setOnClickListener(this);
        btFemaleSelections.setOnClickListener(this);
        btregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btMaleSelection:
                showAlertDialog(R.array.male_sports);
                break;
            case R.id.btFemaleSelection:
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
        if(selectedSports!=null){
            for(int i=0;i<selectedSports.size();i++){
                adapter.add(selectedSports.get(i));
            }
            register();
        }else{
            Toast.makeText(this,"Please select at least one sport!",Toast.LENGTH_LONG).show();
        }
    }

    public void register() {
        fullName = etFullName.getText().toString();
        email = etEmail.getText().toString();
        college = etCollege.getText().toString();
        city = etCity.getText().toString();
        designation=etDesignation.getText().toString();
        contactNum = etContactNo.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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
        /*formData = new ArrayList<NameValuePair>();
        formData.add(new BasicNameValuePair("first_name", firstName));
        formData.add(new BasicNameValuePair("last_name", lastName));
        formData.add(new BasicNameValuePair("email", email));
        formData.add(new BasicNameValuePair("college", college));
        formData.add(new BasicNameValuePair("branch", branch));
        formData.add(new BasicNameValuePair("event", event));
        formData.add(new BasicNameValuePair("contact_number", contactNum));
        new RegisterUserAsyncTask().execute();*/

    }

    class RegisterUserAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;
        // ArrayList<NameValuePair> formData;
//        RegisterUserAsyncTask(ArrayList<NameValuePair> formData){
//            this.formData=formData;
//
//        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage("Registering you! Please wait...");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://spardusers.host56.com/zyro/Registeration.php");
            try {
                httppost.setEntity(new UrlEncodedFormEntity(formData));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Response: " + response, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private JSONObject JsonEncode() {
        JSONObject registrationData = new JSONObject();


        return registrationData;
    }

}
