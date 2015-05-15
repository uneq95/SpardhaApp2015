package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 13-May-15.
 */
public class FragmentUpcomingEvents extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.upcoming_events_layout,container,false);
        return rootView;
    }
}
