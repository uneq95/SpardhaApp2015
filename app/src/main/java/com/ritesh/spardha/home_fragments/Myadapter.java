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

public class Myadapter extends BaseAdapter {
    Context context;
    String[] contentleft;
    int[] picture = {R.drawable.inauguration,R.drawable.sponsors,R.drawable.clsoing_ceremony};


    public Myadapter(Context context){
        this.context = context;
        contentleft = context.getResources().getStringArray(R.array.categories_left);
    }

    @Override
    public int getCount() {
        return contentleft.length;
    }

    @Override
    public Object getItem(int position) {
        return contentleft[position];
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
        titleview.setText(contentleft[position]);
        imageview.setImageResource(picture[position]);

        return row;
    }
}
