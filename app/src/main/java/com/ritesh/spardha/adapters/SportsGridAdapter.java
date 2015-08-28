package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.custom_views.RoundedImageView;
import com.ritesh.spardha.spardha2015.R;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 23-Jun-15.
 */
public class SportsGridAdapter extends BaseAdapter {
    Context context;
    String[] sportsNames;
    int[] sportsIcons = {R.drawable.athletics, R.drawable.cricket, R.drawable.badminton, R.drawable.basketball, R.drawable.volleyball, R.drawable.tabletennis, R.drawable.football, R.drawable.hockey, R.drawable.taekwandoe, R.drawable.tennis};

    public SportsGridAdapter(Context context) {
        this.context = context;
        this.sportsNames=context.getResources().getStringArray(R.array.male_sports);
    }

    @Override
    public int getCount() {
        return sportsNames.length;
    }

    @Override
    public Object getItem(int position) {
        return sportsNames[position];
    }

    @Override
    public long getItemId(int position) {
        return sportsIcons[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.tab_2singleitem, parent, false);
            holder.sportsIcon = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.sportName=(TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sportsIcon.setImageResource(sportsIcons[position]);
        holder.sportName.setText(sportsNames[position]);
        return convertView;
    }

    class ViewHolder {
        ImageView sportsIcon;
        TextView sportName;
    }
}
