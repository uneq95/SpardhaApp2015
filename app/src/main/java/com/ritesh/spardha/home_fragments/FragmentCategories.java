package com.ritesh.spardha.home_fragments;

import android.content.Context;
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

/**
 * Created by ritesh_kumar on 14-May-15.
 */
public class FragmentCategories extends Fragment implements AdapterView.OnItemClickListener{

    CategoriesGridAdapter categoriesGridAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Context activityContext=getActivity();
        String[] categoriesArray=activityContext.getResources().getStringArray(R.array.categories_array);
        categoriesGridAdapter= new CategoriesGridAdapter(activityContext,categoriesArray,R.drawable.bkg1);

        View rootView =inflater.inflate(R.layout.categories_grid_layout,container,false);
        GridView gridView=(GridView)rootView.findViewById(R.id.gridView);
        gridView.setAdapter(categoriesGridAdapter);

        return gridView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
