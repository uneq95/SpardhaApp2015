package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 13-May-15.
 */
public class FragmentUpcomingEvents extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.upcoming_events_layout, container, false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout fl = new LinearLayout(getActivity());
        fl.setLayoutParams(params);

        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .getDisplayMetrics());
        params.setMargins(margin, margin, margin, margin);
        View v;
        v = inflater.inflate(R.layout.upcoming_events_layout, container, false);
        v.setLayoutParams(params);
        fl.addView(v);
        return fl;
    }
}
