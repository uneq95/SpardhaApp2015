package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ritesh.spardha.adapters.SportsGridAdapter;
import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 23-Jun-15.
 */
public class SportsFragment extends Fragment {

    View superView;
    GridView sportsGrid;
    SportsGridAdapter sportsGridAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.sport_layout,container,false);
        sportsGrid = (GridView)superView.findViewById(R.id.gvSportsGrid);
        sportsGridAdapter = new SportsGridAdapter(getActivity().getBaseContext());
        sportsGrid.setAdapter(sportsGridAdapter);
        return superView;
        //return inflater.inflate(R.layout.sport_layout,container,false);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        LinearLayout fl = new LinearLayout(getActivity());
//        fl.setLayoutParams(params);
//
//        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
//                .getDisplayMetrics());
//        params.setMargins(margin, margin, margin, margin);
//        superView.setLayoutParams(params);
//        fl.addView(superView);
//        return fl;
    }
}
