package com.ritesh.spardha.youtube;



import java.util.List;

import com.ritesh.spardha.spardha2015.R;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter{

	
	public final Context context;
	List<RowItem> rowItems;
	public final String[] ThumbUrl;
   
    public CustomListAdapter(Context context,List<RowItem> item,String[] thumnailsUrl){
    	this.context=context;
    	this.rowItems=item;
    	this.ThumbUrl=thumnailsUrl;
    }

    private class ViewHolder{
    	ImageView thumbnail;
    	TextView videoTitle;
    }
	 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	ViewHolder holder=null;
    	LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	if(convertView==null){
    		convertView =inflater.inflate(R.layout.ytd_list_layout, parent,false);
    		holder= new ViewHolder();
    		holder.thumbnail=(ImageView)convertView.findViewById(R.id.LVthumb);
    		holder.videoTitle=(TextView)convertView.findViewById(R.id.tvTitle);
    		convertView.setTag(holder);
    	}else{
    		holder=(ViewHolder)convertView.getTag();
    	}	 
    	RowItem rowItem=(RowItem)getItem(position);
    	Picasso.with(context).load(ThumbUrl[position]).into(holder.thumbnail);
    	holder.videoTitle.setText(rowItem.getTitle());
    	return convertView;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rowItems.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return rowItems.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return rowItems.indexOf(getItem(position));
	}
	
	
	
}
