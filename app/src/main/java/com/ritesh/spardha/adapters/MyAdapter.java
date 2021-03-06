package com.ritesh.spardha.adapters;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ritesh.spardha.spardha2015.R;


/**
 * Created by hp1 on 28-12-2014.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java
    Context ctx;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView textView;
        ImageView imageView;
        ImageButton fb, website, twitter, instagram;

        Context context;

        public ViewHolder(View itemView, int ViewType, Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            this.context = c;
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;
                // setting holder id as 1 as the object being populated are of type item row
            } else {
                fb = (ImageButton) itemView.findViewById(R.id.ib_fblink);
                twitter = (ImageButton) itemView.findViewById(R.id.ib_twitter);
                website = (ImageButton) itemView.findViewById(R.id.ib_webLink);
                instagram = (ImageButton) itemView.findViewById(R.id.ib_instagram);
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }
    }


    public MyAdapter(String Titles[], int Icons[], Context passedContext) {
        // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;                //have seen earlier
        mIcons = Icons;                    //here we assign those passed values to the values we declared here
        //in adapter
        this.ctx = passedContext;

    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false); //Inflating the layout

            //Creating ViewHolder and passing the object of type view
            // Returning the created object
            return new ViewHolder(v, viewType, ctx);

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false); //Inflating the layout

            //Creating ViewHolder and passing the object of type view
            //returning the object created
            return new ViewHolder(v, viewType, ctx);


        }
        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position - 1]);// Settimg the image with array of our icons
        } else {

            /*holder.profile.setImageResource(profile);           // Similarly we set the resources for header view
            holder.Name.setText(name);
            holder.email.setText(email);*/
            holder.fb.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Spardha.IIT.BHU")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    return false;
                }
            });
            holder.twitter.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/spardha_iitbhu")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    return false;
                }
            });
            holder.website.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.spardha.co.in/")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    return false;
                }
            });
            holder.instagram.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/spardha_iitbhu/")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                    return false;
                }
            });

        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length + 1; // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
