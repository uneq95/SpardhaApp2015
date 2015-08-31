package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.ritesh.spardha.custom_views.RoundedImageView;
import com.ritesh.spardha.spardha2015.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ritesh_kumar on 01-Sep-15.
 */
public class TestimonialsAdapter extends BaseAdapter {

    Context context;
    String[] celebLinks = {"http://www.spardha.co.in/img/testi-1.jpg", "http://www.spardha.co.in/img/testi-2.jpg", "http://www.spardha.co.in/img/testi-3.jpg", "http://www.spardha.co.in/img/testi-4.jpg", "http://www.spardha.co.in/img/testi-5.jpg", "http://www.spardha.co.in/img/testi-6.jpg"};
    String[] celebTesti = {"\" This is really awesome, Kudos to you. \"", "\" Believe in Yourself. \"", "\" The level of competition and the atmosphere was amazing here. \"", "\" These young athletes are what makes India proud. \"", "\" This big a festival at just College level, I applaud for you people. \"", "\" Hats off to you. \""};
    String[] celebNames = {"VVS Laxman", "Rajyavardhan Singh Rathore", "Prashanti Singh", "Yogeshwar Dutt (Wrestler)", "Sandeep Singh (Hockey)", "Akhil Kumar (Boxer)"};
    String[] celebDesgn = {"Cricketer", "Silver Awardee- Olympic 2004", "International BasketBall Player", "Bronze Awardee- Olympic 2012", "Ex-Captain - India Team", "Arjuna Awardee"};

    public TestimonialsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return celebNames.length;
    }

    @Override
    public Object getItem(int position) {
        return celebNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.testi_item, parent, false);
            holder.celebImg = (RoundedImageView) convertView.findViewById(R.id.testi_img);
            holder.testi = (TextView) convertView.findViewById(R.id.tvTestDesc);
            holder.name = (TextView) convertView.findViewById(R.id.tvTestiCeleb);
            holder.dsg = (TextView) convertView.findViewById(R.id.tvTestiCelebDesg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(celebLinks[position]).fit().into(holder.celebImg);

        /*Ion.with(context)
                .load(celebLinks[position])
                .intoImageView(holder.celebImg);*/
        holder.testi.setText(celebTesti[position]);
        holder.name.setText(celebNames[position]);
        holder.dsg.setText(celebDesgn[position]);

        return convertView;
    }

    class ViewHolder{
        RoundedImageView celebImg;
        TextView testi,name,dsg;
    }

}
