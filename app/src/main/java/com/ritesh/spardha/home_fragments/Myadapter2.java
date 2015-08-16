package com.ritesh.spardha.home_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by AbhishekPandey on 8/16/2015.
 */
public class Myadapter2 extends BaseAdapter {
    Context context;
    String[] contentright;
    int[] picture = {R.drawable.gallery,R.drawable.informal};


    public Myadapter2(Context context){
        this.context = context;
        contentright = context.getResources().getStringArray(R.array.categories_right);
    }

    @Override
    public int getCount() {
        return contentright.length;
    }

    @Override
    public Object getItem(int position) {
        return contentright[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;
        if (convertView == null){
            LayoutInflater inflating = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =inflating.inflate(R.layout.tab_2singleitem, parent, false);
        }
        else {
            row = convertView;
        }

        TextView titleview = (TextView) row.findViewById(R.id.textView1);
        ImageView imageview = (ImageView) row.findViewById(R.id.imageView1);
        titleview.setText(contentright[position]);
        imageview.setImageResource(picture[position]);

        return row;
    }
}

