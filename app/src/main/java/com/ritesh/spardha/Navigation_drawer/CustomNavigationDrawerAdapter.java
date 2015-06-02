package com.ritesh.spardha.Navigation_drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by Abhishek on 02-06-2015.
 */
public class CustomNavigationDrawerAdapter extends BaseAdapter {
    Context context;
    String[] content;
    int[] picture = {android.R.drawable.ic_menu_my_calendar,android.R.drawable.ic_dialog_map,android.R.drawable.ic_menu_add,android.R.drawable.ic_dialog_dialer,android.R.drawable.btn_star,android.R.drawable.ic_dialog_info};

    public CustomNavigationDrawerAdapter(Context context) {
        this.context = context;
        content = context.getResources().getStringArray(R.array.itemslist);
    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public Object getItem(int position) {
        return content[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        if (convertView == null) {
            LayoutInflater inflating = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflating.inflate(R.layout.custom_nav_drawer, parent, false);

        } else {
            row = convertView;
        }
        TextView titletv = (TextView) row.findViewById(R.id.custumtv);
        ImageView titleiv = (ImageView) row.findViewById(R.id.imageView1);
        titletv.setText(content[position]);
        titleiv.setImageResource(picture[position]);
        return row;
    }
}

