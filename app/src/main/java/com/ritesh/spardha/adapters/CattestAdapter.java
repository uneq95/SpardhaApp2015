package com.ritesh.spardha.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 14-May-15.
 */
public class CattestAdapter extends BaseAdapter {

    int[] iconResId;
    Context context;
    String[] categories,bkgs;

    public CattestAdapter(Context c, String[] categories_array, int[] imageResId,String[] bkgs) {
        this.context = c;
        this.categories = categories_array;
        this.iconResId = imageResId;
        this.bkgs=bkgs;
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
        CardView card;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.cattestlistitemsmall, parent, false);

            holder = new ViewHolder();
            holder.gridItemImage = (ImageView) convertView.findViewById(R.id.imageView3);
            holder.gridItemTitle = (TextView) convertView.findViewById(R.id.textView3);
            holder.card =(CardView)convertView.findViewById(R.id.card_view_hof2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.gridItemTitle.setText(categories[position]);
        holder.gridItemImage.setImageResource(iconResId[position]);
        holder.card.setCardBackgroundColor(Color.parseColor(bkgs[position]));
        /*switch(position){
            case 0:holder.card.setCardBackgroundColor(Color.parseColor("#e74c3c"));break;
            case 1:holder.card.setCardBackgroundColor(Color.parseColor("#3498db"));break;
            case 2:holder.card.setCardBackgroundColor(Color.parseColor("#e19d61"));break;
            case 3:holder.card.setCardBackgroundColor(Color.parseColor("#9b59b6"));break;
        }*/
        return convertView;
    }
}
