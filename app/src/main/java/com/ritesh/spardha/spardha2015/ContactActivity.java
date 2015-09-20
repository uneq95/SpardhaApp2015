package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ritesh.spardha.ContactFunctions.ContactListItem;
import com.ritesh.spardha.adapters.ContactAdapter;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 06-Aug-15.
 */
public class ContactActivity extends AppCompatActivity {
    ListView contactsList ;
    ArrayList<ContactListItem> contacts;
    Context context;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        contactsList =(ListView) findViewById(R.id.lvContactList);
        initContacts();
        context=getBaseContext();
        ContactAdapter adapter = new ContactAdapter(context,contacts);
        contactsList.setAdapter(adapter);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListItem clickedContact=contacts.get(position);
                String number=clickedContact.getPhoneNumber();
                if(number!=null){
                    makeCall(number);
                }
            }
        });
    }


    private void initContacts(){
        contacts = new ArrayList<ContactListItem>();

        contacts.add(new ContactListItem("CORE TEAM"));
        contacts.add(new ContactListItem("Rohit Dixit","rohit.dixit.mst12@iitbhu.ac.in","+917607048644", "http://www.spardha.co.in/img/team/dixit.jpg"));
        contacts.add(new ContactListItem("Shubham Chaudhary","shubham.chaudhary.phe12@iitbhu.ac.in","+917860383824", "http://www.spardha.co.in/img/team/choudhary.jpg"));
        contacts.add(new ContactListItem("Hardik Wadhwa",null,"+918295117656", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Shubham Mittal",null,"+917309601775", R.drawable.ic_no_pic));


        contacts.add(new ContactListItem("EVENT MANAGEMENT TEAM"));
        contacts.add(new ContactListItem("Rajneesh Pujani","rajneesh.mec13@iitbhu.ac.in","+918127544547", "http://www.spardha.co.in/img/team/rajneesh.jpg"));
        contacts.add(new ContactListItem("Rohan Gupta","rohan.gupta.cer13@iitbhu.ac.in","+919548230119", "http://www.spardha.co.in/img/team/rohan.jpg"));
        contacts.add(new ContactListItem("Sunith Singh",null,null, R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("PUBLICITY TEAM"));
        contacts.add(new ContactListItem("Satish Kumar","satish.kumar.cer13@itbhu.ac.in","+917754941947", "http://www.spardha.co.in/img/team/satish.jpg"));
        contacts.add(new ContactListItem("Neeraj Singh Sarwan","nsingh.sarwan.min13@itbhu.ac.in","+918960421891", "http://www.spardha.co.in/img/team/neeraj.jpg"));
        contacts.add(new ContactListItem("Akasmat Pradhan","akasmatbapi@gmail.com",null, R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Siddharth Pathak",null,"+919415980750", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("MARKETING TEAM"));
        contacts.add(new ContactListItem("Harsh Rohan","harsh.rohan.mec13@iitbhu.ac.in","+919619561484", "http://www.spardha.co.in/img/team/harsh.jpg"));
        contacts.add(new ContactListItem("Rahul Dendor","rahul.dendor.cer13@iitbhu.ac.in","+918953882045", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("HOSPITALITY TEAM"));
        contacts.add(new ContactListItem("Sagar Verma","sagar.verma.cer13@iitbhu.ac.in","+917275419847", "http://www.spardha.co.in/img/team/sagar.jpg"));
        contacts.add(new ContactListItem("Abhishek Tomar",null,"+917417886684", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("TECH TEAM"));
        contacts.add(new ContactListItem("Saurav Shekhar","saurav.shekhar.phe13@itbhu.ac.in","+919026829525", "http://www.spardha.co.in/img/team/saurav.jpg"));
        contacts.add(new ContactListItem("Ritesh Kumar","ritesh.kumar.ece13@iitbhu.ac.in","+918953839075", R.drawable.face_rit));
        contacts.add(new ContactListItem("Rajat Sharda","rajat.sharda.cer13@iitbhu.ac.in","+917754941979","http://www.spardha.co.in/img/team/rajat.jpg"));
        contacts.add(new ContactListItem("Gaurav Yadav",null,"+919453888178", "http://www.spardha.co.in/img/team/gaurav.jpg"));
        contacts.add(new ContactListItem("Abhishek Pandey","abhishek.ramakant.che14@iitbhu.ac.in","+919616078405", R.drawable.face_abhishek));


        contacts.add(new ContactListItem("DESIGNING TEAM"));
        contacts.add(new ContactListItem("Yogesh Mahor","yogesh.mahor.cer13@iitbhu.ac.in","+918303073529","http://www.spardha.co.in/img/team/mahor.jpg"));
        contacts.add(new ContactListItem("Suraj",null,null,R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Akshaya Kumar T","takshaya.kumar.civ13@iitbhu.ac.in",null,"http://www.spardha.co.in/img/team/akshay.jpg"));
        contacts.add(new ContactListItem("Sandeep Yadav",null,null,R.drawable.ic_no_pic));


    }

    private void makeCall(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(String.format("tel:%s", number)));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
