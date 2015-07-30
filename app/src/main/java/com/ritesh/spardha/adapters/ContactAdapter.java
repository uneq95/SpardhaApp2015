package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.ContactFunctions.ContactListItem;
import com.ritesh.spardha.spardha2015.R;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 22-Jul-15.
 */
public class ContactAdapter extends BaseAdapter{


    Context context;
    ArrayList<ContactListItem> contacts;
    public ContactAdapter(Context context, ArrayList<ContactListItem> objects) {
        this.context=context;
        contacts=objects;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.indexOf(contacts.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder= new ViewHolder();
        final ContactListItem listItem = contacts.get(position);
            LayoutInflater inflater = LayoutInflater.from(context);
            if(contacts.get(position).isHeader()){
                //header =true
                convertView = inflater.inflate(R.layout.contact_headers,parent,true);
                holder.tvHeaderText=(TextView)convertView.findViewById(R.id.contact_header);
                holder.tvHeaderText.setText(listItem.getContactHeader());
                convertView.setClickable(false);
                //convertView.setEnabled(false);
            }else{
                //for contacts
                convertView = inflater.inflate(R.layout.single_contact_layout2,parent,true);
                holder.tvContactName=(TextView)convertView.findViewById(R.id.tvTeamMemberName);
                holder.tvDesignation=(TextView)convertView.findViewById(R.id.tvTeamMemberEmail);
                holder.ivContactPic=(ImageView)convertView.findViewById(R.id.ivTeamMemberPic);
                holder.tvContactName.setText(listItem.getContactName());
                holder.tvDesignation.setText(listItem.getEmail());
                holder.ivContactPic.setImageResource(listItem.getPhotoResId());
                /*convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContactsHelper callerInstance= new ContactsHelper(listItem.getPhoneNumber(),context);
                        callerInstance.makeCall();
                    }
                });*/
            }
        return convertView;
    }



    class ViewHolder{
        //for contacts
        TextView tvContactName,tvDesignation;
        ImageView ivContactPic;

        //for header
        TextView tvHeaderText;

    }
}
