package com.ritesh.spardha.registration;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by Abhishek on 09-06-2015.
 */
public class RegistrationForm extends Activity implements View.OnClickListener {

    EditText firstname,lastname,email,college,branch,event,contactno;
    Button registerbutton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        initializevariable();
        registerbutton.setOnClickListener(this);

    }

    private void initializevariable() {
        firstname = (EditText) findViewById(R.id.userfirstname);
        lastname = (EditText) findViewById(R.id.userlastname);
        email = (EditText) findViewById(R.id.useremailid);
        college = (EditText) findViewById(R.id.usercollegename);
        branch = (EditText) findViewById(R.id.userbranch);
        event = (EditText) findViewById(R.id.userparticipation);
        contactno = (EditText) findViewById(R.id.userphone);
        registerbutton = (Button) findViewById(R.id.registerbutton);

    }

    @Override
    public void onClick(View v) {
        if(firstname.getText().length()>0){
            if(email.getText().length()>0){
                if(college.getText().length()>0){
                    if(branch.getText().length()>0){
                        if(event.getText().length()>0){
                            if(contactno.getText().length()>9){
                                Toast.makeText(this,"You have successfully registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(this,"Enter Valid No.", Toast.LENGTH_SHORT).show();
                                contactno.requestFocus();
                            }
                        }else{
                            Toast.makeText(this,"Field can't be left empty", Toast.LENGTH_SHORT).show();
                            event.requestFocus();
                        }
                    }else{
                        Toast.makeText(this,"Enter your Branch", Toast.LENGTH_SHORT).show();
                        branch.requestFocus();
                    }
                }else{
                    Toast.makeText(this,"Enter your college Name", Toast.LENGTH_SHORT).show();
                    college.requestFocus();
                }
            }else{
                Toast.makeText(this,"Enter your Email-id", Toast.LENGTH_SHORT).show();
                email.requestFocus();
            }
        }else{
            Toast.makeText(this,"Enter your Name", Toast.LENGTH_SHORT).show();
            firstname.requestFocus();
        }
    }
}
