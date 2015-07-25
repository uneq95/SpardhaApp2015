package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ritesh.spardha.ContactFunctions.ContactListItem;
import com.ritesh.spardha.ContactFunctions.ContactsHelper;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        superView=inflater.inflate(R.layout.contacts_layout,container,false);
        contactsList =(ListView) superView.findViewById(R.id.lvContactList);
        initContacts();
        ContactAdapter adapter = new ContactAdapter(getActivity().getBaseContext(),contacts);
        contactsList.setAdapter(adapter);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(contacts.get(position).getPhoneNumber());
                if(contacts.get(position).getPhoneNumber()!=null){
                    ContactsHelper callerInstance= new ContactsHelper(contacts.get(position).getPhoneNumber(),getActivity());
                    callerInstance.makeCall();
                }
            }
        });
        return superView;
    }
    private void initContacts(){
        contacts = new ArrayList<ContactListItem>();

        contacts.add(new ContactListItem("TECH TEAM"));
        contacts.add(new ContactListItem("Ritesh Kumar","Android Developer","8953839075", R.drawable.face_rit));
        contacts.add(new ContactListItem("Abhishek Pandey","Android Developer","9616078405", R.drawable.face_rit));
        contacts.add(new ContactListItem("Rajat Sharda","Web Developer (Frontend)","7754941979", R.drawable.rajat));

        contacts.add(new ContactListItem("CORE TEAM"));
        contacts.add(new ContactListItem("Rajat Sharda","Event Manager","7754941979", R.drawable.rajat));
        contacts.add(new ContactListItem("YOGESH MAHOR","Photoshooter","", R.drawable.face_rit));
        contacts.add(new ContactListItem("Gaurav Yadav","Designer","", R.drawable.face_rit));

        contacts.add(new ContactListItem("PUBLICITY TEAM"));
        contacts.add(new ContactListItem("Rajat Sharda","Event Manager","7754941979", R.drawable.face_rit));
        contacts.add(new ContactListItem("YOGESH MAHOR","Photoshooter","", R.drawable.face_rit));
        contacts.add(new ContactListItem("Gaurav Yadav","Designer","", R.drawable.face_rit));

        contacts.add(new ContactListItem("MARKETING TEAM"));
        contacts.add(new ContactListItem("Rajat Sharda","Event Manager","7754941979", R.drawable.face_rit));
        contacts.add(new ContactListItem("YOGESH MAHOR","Photoshooter","", R.drawable.face_rit));
        contacts.add(new ContactListItem("Gaurav Yadav","Designer","", R.drawable.face_rit));

        contacts.add(new ContactListItem("EVENT MANAGEMENT TEAM"));
        contacts.add(new ContactListItem("Rajat Sharda","Event Manager","7754941979", R.drawable.face_rit));
        contacts.add(new ContactListItem("YOGESH MAHOR","Photoshooter","", R.drawable.face_rit));
        contacts.add(new ContactListItem("Gaurav Yadav","Designer","", R.drawable.face_rit));

        contacts.add(new ContactListItem("HOSPITALITY TEAM"));
        contacts.add(new ContactListItem("Rajat Sharda","Event Manager","7754941979", R.drawable.face_rit));
        contacts.add(new ContactListItem("YOGESH MAHOR","Photoshooter","", R.drawable.face_rit));
        contacts.add(new ContactListItem("Gaurav Yadav","Designer","", R.drawable.face_rit));

    }
}
