package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ritesh.spardha.custom_views.RoundedImageView;
import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 23-Jun-15.
 */
public class SportsGridAdapter extends BaseAdapter {
    Context context;
    String[] sportsNames = {"Athletics", "Cricket", "Badminton", "Basketball", "Volleyball", "Table Tennis", "Football", "Hockey", "Taekwondo", "Lawn Tennis"};
    int[] sportsIcons = {R.drawable.athletics, R.drawable.cricket, R.drawable.badminton, R.drawable.basketball, R.drawable.volleyball, R.drawable.tabletennis, R.drawable.football, R.drawable.hockey, R.drawable.taekwandoe, R.drawable.tennis};

    public SportsGridAdapter(Context context) {
        this.context = context;
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
            convertView = inflater.inflate(R.layout.sports_grid_item, parent, false);
            holder.ivSportsIcon = (RoundedImageView) convertView.findViewById(R.id.ivSportsIcon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivSportsIcon.setImageResource(sportsIcons[position]);
        return convertView;
    }

    class ViewHolder {
        RoundedImageView ivSportsIcon;
    }
}
