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
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Toolbar toolbar;
    EditText etFullName, etEmail, etCollege, etBranch, etContactNo;
    Button btregister, btIndividual, btContingent;
    String fullName, email, college, branch, contactNum;
    ArrayList<NameValuePair> formData;
    ArrayList<String> selectedNonAthletics, selectedAthletics;
    String selectedNonAthleticSport=null, selectedAthleticSport;
    boolean isIndividual;

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
        etBranch = (EditText) findViewById(R.id.etBranch);
        etContactNo = (EditText) findViewById(R.id.etContactNumber);
        btIndividual = (Button) findViewById(R.id.btIndividual);
        btContingent = (Button) findViewById(R.id.btContingent);
        btregister = (Button) findViewById(R.id.btRegister);
        btContingent.setOnClickListener(this);
        btIndividual.setOnClickListener(this);
        btregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final String[] nonAthletics = this.getResources().getStringArray(R.array.non_athletics_sports);
        final String[] athleticsSports = RegisterActivity.this.getResources().getStringArray(R.array.athletics);

        switch (v.getId()) {
            case R.id.btContingent:
                selectedNonAthletics = new ArrayList<>();
                selectedAthletics = new ArrayList<>();
                isIndividual=false;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select sports");
                builder.setMultiChoiceItems(nonAthletics, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked) {
                            selectedNonAthletics.add(nonAthletics[which]);
                        } else if (selectedNonAthletics.contains(nonAthletics[which])) {
                            selectedNonAthletics.remove(nonAthletics[which]);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Display selected sports
                    }
                });
                builder.setNeutralButton("Choose Athletics Here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //final String[] athleticsSports=RegisterActivity.this.getResources().getStringArray(R.array.athletics);
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("Athletics");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Display selected sports
                            }
                        });
                        builder.setMultiChoiceItems(athleticsSports, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                if (isChecked) {
                                    selectedAthletics.add(athleticsSports[which]);
                                } else if (selectedNonAthletics.contains(athleticsSports[which])) {
                                    selectedAthletics.remove(athleticsSports[which]);
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedNonAthletics = null;
                                selectedAthletics = null;
                            }
                        });
                        builder.create();
                        builder.show();

                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.btIndividual:
                isIndividual=true;
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Select sports");
                builder2.setSingleChoiceItems(nonAthletics, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedNonAthleticSport = nonAthletics[which];
                    }
                });
                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Display selected sports
                    }
                });
                builder2.setNeutralButton("Choose Athletics Here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String[] athleticsSports = RegisterActivity.this.getResources().getStringArray(R.array.athletics);
                        selectedAthletics = new ArrayList<>();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("Athletics");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Display selected sports
                            }
                        });
                        builder.setMultiChoiceItems(athleticsSports, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                if (isChecked) {
                                    selectedAthletics.add(athleticsSports[which]);
                                } else if (selectedNonAthletics.contains(athleticsSports[which])) {
                                    selectedAthletics.remove(athleticsSports[which]);
                                }
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedNonAthletics = null;
                                selectedAthletics = null;
                            }
                        });
                        builder.create();
                        builder.show();

                    }
                });
                builder2.create();
                builder2.show();
                break;
            case R.id.btRegister:
                reg();
                break;


        }


    }

    private void reg() {
        if (etFullName.getText().length() > 0) {
            if (etEmail.getText().length() > 0) {
                if (etCollege.getText().length() > 0) {
                    if (etBranch.getText().length() > 0) {

                        if (etContactNo.getText().length() == 10) {
                            eventCheck();
                        } else {
                            etContactNo.setError("Invalid Mobile Number!");
                            etContactNo.requestFocus();
                        }

                    } else {
                        etBranch.setError("Enter your Branch!");
                        etBranch.requestFocus();
                    }
                } else {
                    etCollege.setError("Enter your college Name!");
                    etCollege.requestFocus();
                }
            } else {
                etEmail.setError( "Enter your Email-id!");
                etEmail.requestFocus();
            }
        } else {
            etFullName.setError("Enter your Name!");
            etFullName.requestFocus();
        }
    }

    private void eventCheck(){
        if(isIndividual){
            if(selectedNonAthleticSport!=null||selectedNonAthletics.size()>0){
                register();
            }else{
                Toast.makeText(this,"Please select ATLEAST ONE sport!",Toast.LENGTH_LONG).show();
            }
        }else{
            if(selectedNonAthletics.size()>0||selectedNonAthletics.size()>0){
                register();
            }else{
                Toast.makeText(this,"Please select a sport!",Toast.LENGTH_LONG).show();
            }

        }
    }
    public void register() {
        fullName = etFullName.getText().toString();
        email = etEmail.getText().toString();
        college = etCollege.getText().toString();
        branch = etBranch.getText().toString();
        contactNum = etContactNo.getText().toString();
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("You are registering in the following sports:");
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
