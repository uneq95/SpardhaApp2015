package com.ritesh.spardha.spardha2015;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 06-Aug-15.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    Toolbar toolbar;
    View superView;
    EditText etFirstName, etLastName, etEmail, etCollege, etBranch, etEvent, etContactno;
    Button btregister;
    String firstName, lastName, fullName, email, college, branch, event, contactNum;
    ArrayList<NameValuePair> formData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getBaseContext();
        setContentView(R.layout.registration_layout_new);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public void onClick(View v) {
        if (etFirstName.getText().length() > 0) {
            if (etEmail.getText().length() > 0) {
                if (etCollege.getText().length() > 0) {
                    if (etBranch.getText().length() > 0) {
                        if (etEvent.getText().length() > 0) {
                            if (etContactno.getText().length() == 10) {
                                register();
                            } else {
                                etContactno.setError("Invalid Mobile Number!");
                                Toast.makeText(context, "Enter Valid No.", Toast.LENGTH_SHORT).show();
                                etContactno.requestFocus();
                            }
                        } else {
                            Toast.makeText(context, "Aren't you participating in any event?", Toast.LENGTH_SHORT).show();
                            etEvent.setError("cant be left empty");
                            etEvent.requestFocus();
                        }
                    } else {
                        Toast.makeText(context, "Enter your Branch", Toast.LENGTH_SHORT).show();
                        etBranch.requestFocus();
                    }
                } else {
                    Toast.makeText(context, "Enter your college Name", Toast.LENGTH_SHORT).show();
                    etCollege.requestFocus();
                }
            } else {
                Toast.makeText(context, "Enter your Email-id", Toast.LENGTH_SHORT).show();
                etEmail.requestFocus();
            }
        } else {
            Toast.makeText(context, "Enter your Name", Toast.LENGTH_SHORT).show();
            etFirstName.requestFocus();
        }

    }

    public void register() {
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        email = etEmail.getText().toString();
        college = etCollege.getText().toString();
        branch = etBranch.getText().toString();
        event = etEvent.getText().toString();
        contactNum = etContactno.getText().toString();
        formData = new ArrayList<NameValuePair>();
        formData.add(new BasicNameValuePair("first_name",firstName));
        formData.add(new BasicNameValuePair("last_name",lastName));
        formData.add(new BasicNameValuePair("email",email));
        formData.add(new BasicNameValuePair("college",college));
        formData.add(new BasicNameValuePair("branch",branch));
        formData.add(new BasicNameValuePair("event",event));
        formData.add(new BasicNameValuePair("contact_number",contactNum));
        new RegisterUserAsyncTask().execute();

    }

    class RegisterUserAsyncTask extends AsyncTask<Void,Void,Void> {

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
                final String response= httpclient.execute(httppost,responseHandler);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"Response: "+response,Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private JSONObject JsonEncode(){
        JSONObject registrationData=new JSONObject();


        return registrationData;
    }

}
