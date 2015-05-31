package com.ritesh.spardha.home_fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ritesh.spardha.adapters.CategoriesGridAdapter;
import com.ritesh.spardha.spardha2015.FadingActionBar;
import com.ritesh.spardha.spardha2015.LocationActivity;
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
        gridView.setOnItemClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout fl = new LinearLayout(getActivity());
        fl.setLayoutParams(params);

        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .getDisplayMetrics());
        params.setMargins(margin, margin, margin, margin);
        rootView.setLayoutParams(params);
        fl.addView(rootView);
        return fl;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==4){
            Toast.makeText(getActivity(),"Here is the gallery..",Toast.LENGTH_SHORT).show();
        }if(position==3){
            startActivity(new Intent(getActivity(), LocationActivity.class));
//            Uri gmmIntentUri=Uri.parse("http://maps.google.com/maps?saddr="+"28.5747500"+","+"77.3204900"+"&daddr="+"28.5701755"+","+"77.3253555");
//            //Uri.parse("http://maps.google.com/maps?saddr="+src_lat+","+src_ltg+"&daddr="+des_lat+","+des_ltg))
//            //Uri gmmIntentUri = Uri.parse("google.navigation:q=India+Gate,+Delhi+India");
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//            mapIntent.setPackage("com.google.android.apps.maps");
//            startActivity(mapIntent);
        }if(position==1){
            startActivity(new Intent(getActivity(), FadingActionBar.class));
        }


    }
}
