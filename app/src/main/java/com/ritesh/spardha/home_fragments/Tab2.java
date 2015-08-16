package com.ritesh.spardha.home_fragments;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

public class Tab2 extends Fragment {

    ListView lv_left, lv_right;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final  View v =  inflater.inflate(R.layout.tab_2, container, false);


        lv_left = (ListView) v.findViewById(R.id.listViewleft);
        lv_right = (ListView) v.findViewById(R.id.listViewright);
        lv_left.setAdapter(new Myadapter(v.getContext()));
        lv_right.setAdapter(new Myadapter2(v.getContext()));
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(v.getContext(), "Inauguration clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(v.getContext(), "Sponsors clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(v.getContext(), "Closing Ceremony clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });


        lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(v.getContext(), "Gallery clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(v.getContext(), "Informal clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return v;
    }


}