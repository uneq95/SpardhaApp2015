package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 09-Jun-15.
 */
public class MapsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.iit_bhu_navigator_layout,container,false);
    }
}
