package com.ritesh.spardha.Navigation_drawer;

/**
 * Created by Abhishek on 02-06-2015.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.LocationActivity;
import com.ritesh.spardha.spardha2015.R;

public class NavigationDrawer extends Fragment implements AdapterView.OnItemClickListener /*implements AdapterView.OnItemClickListener */{
    DrawerLayout drawer;
    ListView lv;
    String[] content;
    CustomNavigationDrawerAdapter myad;
    View v;
    private ActionBarDrawerToggle abdt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.navigationdrawerlayout, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) v.findViewById(R.id.drawerlist);
        content = v.getResources().getStringArray(R.array.itemslist);
        myad = new CustomNavigationDrawerAdapter(v.getContext());
        lv.setAdapter(myad);
       // lv.setAdapter(new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1,content));
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    if(position==1){
            startActivity(new Intent(getActivity(), LocationActivity.class));
//            Uri gmmIntentUri=Uri.parse("http://maps.google.com/maps?saddr="+"28.5747500"+","+"77.3204900"+"&daddr="+"28.5701755"+","+"77.3253555");
//            //Uri.parse("http://maps.google.com/maps?saddr="+src_lat+","+src_ltg+"&daddr="+des_lat+","+des_ltg))
//            //Uri gmmIntentUri = Uri.parse("google.navigation:q=India+Gate,+Delhi+India");
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent);

    }
    }

}