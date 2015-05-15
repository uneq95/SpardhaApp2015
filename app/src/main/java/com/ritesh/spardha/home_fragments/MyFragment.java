package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 13-May-15.
 */
public class MyFragment extends Fragment {


    private static final String ARG_POSITION = "position";

    private int position;

    public static MyFragment newInstance(int position) {
        MyFragment f = new MyFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout fl = new LinearLayout(getActivity());
        fl.setLayoutParams(params);

        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .  getDisplayMetrics());
        params.setMargins(margin,margin,margin,margin);
        View v1,v2;
        v1=inflater.inflate(R.layout.x_vs_y_layout,container,false);
        v2=inflater.inflate(R.layout.x_vs_y_layout,container,false);
        ViewGroup.LayoutParams viewParams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        TextView v = new TextView(getActivity());

        v1.setLayoutParams(params);

        v2.setLayoutParams(params);
//        v.setGravity(Gravity.CENTER);
//        v.setBackgroundResource(R.drawable.background_card);
//        v.setText("CARD " + (position + 1));
        fl.addView(v2);
        fl.addView(v1);
        return fl;
    }
}
