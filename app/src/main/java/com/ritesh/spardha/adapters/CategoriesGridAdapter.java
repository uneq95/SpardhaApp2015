package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 14-May-15.
 */
public class CategoriesGridAdapter extends BaseAdapter {

    int[] iconResId;
    Context context;
    String[] categories;

    public CategoriesGridAdapter(Context c, String[] categories_array, int[] imageResId) {
        this.context = c;
        this.categories = categories_array;
        this.iconResId = imageResId;
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        ImageView gridItemImage;
        TextView gridItemTitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tab_2singleitem, parent, false);
            holder = new ViewHolder();
            holder.gridItemImage = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.gridItemTitle = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gridItemTitle.setText(categories[position]);
        holder.gridItemImage.setImageResource(iconResId[position]);
        return convertView;
    }
}
