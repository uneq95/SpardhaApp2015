package com.ritesh.spardha.home_fragments;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ritesh.spardha.adapters.CategoriesGridAdapter;
import com.ritesh.spardha.spardha2015.R;
import com.ritesh.spardha.spardha2015.SponsersActivity;

public class Tab2 extends Fragment {

    GridView gridView;
    View superView;
    CategoriesGridAdapter adapter;
    String[] categories={"Inaugration","Events","Informals","Sponsers","Gallery","Closing","Testimonials"};
    int[] resId={R.drawable.inauguration,R.drawable.informal,R.drawable.informal,R.drawable.sponsors,R.drawable.gallery,R.drawable.clsoing_ceremony,R.drawable.clsoing_ceremony};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.categories_grid_layout, container, false);
        gridView = (GridView) superView.findViewById(R.id.gridView);
        adapter= new CategoriesGridAdapter(getActivity().getBaseContext(),categories,resId);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 3: startActivity(new Intent(getActivity(), SponsersActivity.class));break;
                }
            }
        });
        return superView;
    }


}