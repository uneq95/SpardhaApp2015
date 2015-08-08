package com.ritesh.spardha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.gcm.GcmMessage;
import com.ritesh.spardha.spardha2015.R;

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
                holder.tvMatchGenre.setText(msg.getSport());
                holder.tvVenue.setText(msg.getLocation());
                holder.tvDateTime.setText(String.format("%s , %s",msg.getTime(),msg.getDate()));break;
            case 2:
                convertView = inflater.inflate(R.layout.gcm_msg2, parent, false);
                holder.iv_gcm2_pic = (ImageView) convertView.findViewById(R.id.iv_gcm2_pic);
                holder.tv_gcm2_msgbody = (TextView) convertView.findViewById(R.id.tv_gcm2_msgbody);
                //set image resource using picassa
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
        TextView tvMatchGenre, tvVenue, tvDateTime;
        //for msg type3
        TextView tv_gcm1_title, tv_gcm1_msg_body;
        //for msg type2
        ImageView iv_gcm2_pic;
        TextView tv_gcm2_msgbody;
    }
}
