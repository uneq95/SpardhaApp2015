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

    Context context;
    String[] categories;
    int ImageResId;
    private static LayoutInflater inflater=null;
    public CategoriesGridAdapter(Context c, String[] categories_array, int imageResId) {
        this.context = c;
        this.categories = categories_array;
        this.ImageResId = imageResId;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public class ViewHolder{
        ImageView gridItemImage;
        TextView gridItemTitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= new ViewHolder();
        View gridItemView;
        gridItemView=inflater.inflate(R.layout.grid_item_layout,null);
        viewHolder.gridItemImage.setImageResource(ImageResId);
        viewHolder.gridItemTitle.setText(categories[position]);
        gridItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"you clicked "+categories[position],Toast.LENGTH_SHORT);
            }
        });
        return gridItemView;
    }
}
