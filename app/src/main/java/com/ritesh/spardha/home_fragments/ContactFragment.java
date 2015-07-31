package com.ritesh.spardha.home_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ritesh.spardha.ContactFunctions.ContactListItem;
import com.ritesh.spardha.adapters.ContactAdapter;
import com.ritesh.spardha.spardha2015.R;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 22-Jul-15.
 */
public class ContactFragment extends Fragment {

    View superView;
    ListView contactsList ;
    ArrayList<ContactListItem> contacts;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        superView=inflater.inflate(R.layout.contacts_layout,container,false);
        contactsList =(ListView) superView.findViewById(R.id.lvContactList);
        initContacts();
        context=getActivity().getBaseContext();
        ContactAdapter adapter = new ContactAdapter(getActivity().getBaseContext(),contacts);
        contactsList.setAdapter(adapter);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListItem clickedContact=contacts.get(position);
                if(clickedContact.getPhoneNumber()!=null){
                    makeCall(clickedContact.getPhoneNumber());
                }
            }
        });
        return superView;
    }
    private void initContacts(){
        contacts = new ArrayList<ContactListItem>();

        contacts.add(new ContactListItem("CORE TEAM"));
        contacts.add(new ContactListItem("Rohit Dixit","rohit.dixit.mst12@iitbhu.ac.in","+917607048644", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Shubham Chaudhary","shubham.chaudhary.phe12@iitbhu.ac.in","+917860383824", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Hardik Wadwa",null,null, R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Shubham Mittal",null,null, R.drawable.ic_no_pic));


        contacts.add(new ContactListItem("PUBLICITY TEAM"));
        contacts.add(new ContactListItem("Satish Kumar","satish.kumar.cer13@itbhu.ac.in","+917754941979", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Neeraj Singh Sarwan","nsingh.sarwan.min13@itbhu.ac.in","+918445023246", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Akasmat Pradhan","akasmatbapi@gmail.com",null, R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Siddharth Pathak",null,"+919415980750", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("MARKETING TEAM"));
        contacts.add(new ContactListItem("Harsh Rohan","harsh.rohan.mec13@itbhu.ac.in","+919619561484", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Raghav Gautam","raghav1408@gmail.com","+917275418410", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("HOSPITALITY TEAM"));
        contacts.add(new ContactListItem("Sagar Verma","sagar.verma.cer13@itbhu.ac.in","+917275419847", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Abhinav Vaid",null,null, R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Abhishek Tomar",null,null, R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("EVENT MANAGEMENT TEAM"));
        contacts.add(new ContactListItem("Rajneesh Pujani","rajneesh.mec13@itbhu.ac.in","+918127544547", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Rohan Gupta","rohan.gupta.cer13@itbhu.ac.in","+919548230119", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Sunith Singh",null,null, R.drawable.ic_no_pic));


        contacts.add(new ContactListItem("TECH TEAM"));
        contacts.add(new ContactListItem("Ritesh Kumar","ritesh.kumar.ece13@itbhu.ac.in","+918953839075", R.drawable.face_rit));
        contacts.add(new ContactListItem("Abhishek Pandey","abhishek.pandey.che14@itbhu.ac.in","+919616078405", R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Rajat Sharda","rajat.sharda.cer13@itbhu.ac.in","+917754941979", R.drawable.rajat));
        contacts.add(new ContactListItem("Gaurav Yadav",null,"9453888178", R.drawable.ic_no_pic));

        contacts.add(new ContactListItem("Designing Team"));
        contacts.add(new ContactListItem("Yogesh Mahor","yogesh.mahor.cer13@itbhu.ac.in","+918303073529",R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Suraj",null,null,R.drawable.ic_no_pic));
        contacts.add(new ContactListItem("Akshay","takshaya.kumar.civ13@itbhu.ac.in",null,R.drawable.ic_no_pic));


    }

    private void makeCall(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(String.format("tel:%s", number)));
        context.startActivity(intent);
    }
}
