package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.ritesh.spardha.spardha2015.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ritesh_kumar on 16-Aug-15.
 */
public class SponserListAdapter extends BaseAdapter {
    //String[] sponsers;
    Context context;
    String[] sponsers ={"http://www.spardha.co.in/img/sponsors/hp.png",
            "http://www.spardha.co.in/img/sponsors/ucobank.png",
            "http://www.spardha.co.in/img/sponsors/pepsi.png",
            "http://www.spardha.co.in/img/sponsors/bob.png",
            "http://www.spardha.co.in/img/sponsors/paytm.png",
            "http://www.spardha.co.in/img/sponsors/nokia.png",
            "http://www.spardha.co.in/img/sponsors/baskin.png",
            "http://www.spardha.co.in/img/sponsors/amul.png",
            "http://www.spardha.co.in/img/sponsors/dominos.png",
            "http://www.spardha.co.in/img/sponsors/mahindra.png",
            "http://www.spardha.co.in/img/sponsors/godrej.png",
            "http://www.spardha.co.in/img/sponsors/sail.png",
            "http://www.spardha.co.in/img/sponsors/intel.png",
            "http://www.spardha.co.in/img/sponsors/peta.png",
            "http://www.spardha.co.in/img/sponsors/long/bsa.png",
            "http://www.spardha.co.in/img/sponsors/long/her.png",
            "http://www.spardha.co.in/img/sponsors/long/jabong.png",
            "http://www.spardha.co.in/img/sponsors/long/reebok.png",
            "http://www.spardha.co.in/img/sponsors/long/ht.png"

    };

    public SponserListAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return sponsers.length;
    }

    @Override
    public Object getItem(int position) {
        return sponsers[position];
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
            convertView = inflater.inflate(R.layout.sponser_listitem_layout, parent, false);
            holder.sponserImg = (ImageView) convertView.findViewById(R.id.ivSponserImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ion.with(context)
                .load(sponsers[position])
                .withBitmap()
                .intoImageView(holder.sponserImg);
       // Picasso.with(context).load(sponsers[position]).fit().into(holder.sponserImg);
        return convertView;
    }

    class ViewHolder{
        ImageView sponserImg;
    }
}
