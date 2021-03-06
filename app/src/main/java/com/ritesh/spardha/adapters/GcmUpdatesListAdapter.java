package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.ritesh.spardha.custom_views.RoundedImageView;
import com.ritesh.spardha.gcm.GcmMessage;
import com.ritesh.spardha.spardha2015.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 08-Aug-15.
 */
public class GcmUpdatesListAdapter extends BaseAdapter {

    Context context;
    ArrayList<GcmMessage> msgs;

    public GcmUpdatesListAdapter(Context context, ArrayList<GcmMessage> msgs) {
        this.context = context;
        this.msgs = msgs;
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return msgs.indexOf(msgs.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        GcmMessage msg = msgs.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (msg.getMsgType()) {
            case 1:
                convertView = inflater.inflate(R.layout.x_vs_y_layout, parent, false);
                holder.tvMatchGenre = (TextView) convertView.findViewById(R.id.tvMatchGenre);
                holder.tvVenue = (TextView) convertView.findViewById(R.id.tvVenue);
                holder.tvDateTime = (TextView) convertView.findViewById(R.id.tvDateTime);
                holder.team1Image=(RoundedImageView) convertView.findViewById(R.id.ivTeam1);
                holder.team2Image=(RoundedImageView) convertView.findViewById(R.id.ivTeam2);
                holder.tvTeam1=(TextView)convertView.findViewById(R.id.tvTeam1);
                holder.tvTeam2=(TextView)convertView.findViewById(R.id.tvTeam2);
                holder.tvTeam1.setText(msg.getTeam1());
                holder.tvTeam2.setText(msg.getTeam2());
//                Toast.makeText(context,msg.getTeam1ImgLink(),Toast.LENGTH_SHORT).show();
//                Ion.with(context).load(msg.getTeam1ImgLink()).intoImageView(holder.team1Image);
//                Ion.with(context).load(msg.getTeam2ImgLink()).intoImageView(holder.team2Image);
                Picasso.with(context).load(msg.getTeam1ImgLink()).into(holder.team1Image);
                Picasso.with(context).load(msg.getTeam2ImgLink()).into(holder.team2Image);
                holder.tvMatchGenre.setText(msg.getSport());
                holder.tvVenue.setText(msg.getLocation());
                holder.tvDateTime.setText(String.format("%s , %s",msg.getTime(),msg.getDate()));break;
            case 2:
                convertView = inflater.inflate(R.layout.gcm_msg2, parent, false);
                holder.iv_gcm2_pic = (ImageView) convertView.findViewById(R.id.iv_gcm2_pic);
                holder.tv_gcm2_msgbody = (TextView) convertView.findViewById(R.id.tv_gcm2_msgbody);
                //set image resource using picassa
                Picasso.with(context).load(msg.getPhotoLink()).into(holder.iv_gcm2_pic);
                holder.tv_gcm2_msgbody.setText(msg.getImageLinkedMsg());break;

            case 3:
                convertView = inflater.inflate(R.layout.gcm_msg_1_simple_msg, parent, false);
                holder.tv_gcm1_title = (TextView) convertView.findViewById(R.id.tv_gcm1_title);
                holder.tv_gcm1_msg_body = (TextView) convertView.findViewById(R.id.tv_gcm1_msg_body);
                holder.tv_gcm1_title.setText(msg.getMsgTitle());
                holder.tv_gcm1_msg_body.setText(msg.getMsgBody());break;
        }
        return convertView;
    }

    class ViewHolder {
        //for msg type1
        TextView tvMatchGenre, tvVenue, tvDateTime,tvTeam1,tvTeam2;
        RoundedImageView team1Image,team2Image;
        //for msg type3
        TextView tv_gcm1_title, tv_gcm1_msg_body;
        //for msg type2
        ImageView iv_gcm2_pic;
        TextView tv_gcm2_msgbody;
    }
}
