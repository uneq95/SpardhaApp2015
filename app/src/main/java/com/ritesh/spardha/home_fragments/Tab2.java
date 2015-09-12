package com.ritesh.spardha.home_fragments;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.ritesh.spardha.CustomizeGallery.GalleryMainActivity;
import com.ritesh.spardha.adapters.CategoriesGridAdapter;
import com.ritesh.spardha.events.Event_List;
import com.ritesh.spardha.pinterest.Data;
import com.ritesh.spardha.pinterest.DataAdapter;
import com.ritesh.spardha.pinterest.MainActivity;
import com.ritesh.spardha.spardha2015.R;
import com.ritesh.spardha.spardha2015.SponsersActivity;
import com.ritesh.spardha.spardha2015.Sportcard;
import com.ritesh.spardha.spardha2015.TestimonialActivity;
import com.ritesh.spardha.youtube.YtpChannel;

import java.util.ArrayList;

public class Tab2 extends Fragment implements AdapterView.OnItemClickListener {

    View superView;

    private StaggeredGridView mGridView;
    private DataAdapter mAdapter;
    ArrayList<Data> eventsObjects;

    String[] categories = {"About Spardha","Inaugration", "Events", "Sponsers", "Gallery","Youtube", "Testimonials"};
    int[] resId = {R.drawable.about_us,R.drawable.inauguration, R.drawable.informal, R.drawable.sponsors, R.drawable.gallery,R.drawable.youtube , R.drawable.testimonials};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.pinterst_cat, container, false);
        mGridView = (StaggeredGridView) superView.findViewById(R.id.grid_view);
        genData();

        mAdapter = new DataAdapter(getActivity(), R.layout.list_item_sample, eventsObjects);

        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(this);


       /* gridView = (GridView) superView.findViewById(R.id.gridView);
        adapter= new CategoriesGridAdapter(getActivity().getBaseContext(),categories,resId);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){


                    case 0:
                        Toast.makeText(superView.getContext(),"TO be uploaded soon",Toast.LENGTH_SHORT).show();
                        break;
                    case 1: startActivity(new Intent(getActivity(), Event_List.class));break;
                    case 2:
                        Toast.makeText(superView.getContext(),"TO be uploaded soon",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), Sportcard.class));
                        break;
                    case 3: startActivity(new Intent(getActivity(), SponsersActivity.class));break;
                    case 4:startActivity(new Intent(getActivity(), GalleryMainActivity.class));break;
                    case 5: startActivity(new Intent(getActivity(), YtpChannel.class));break;
                    case 6: startActivity(new Intent(getActivity(),TestimonialActivity.class));break;


                }
            }
        });*/
        return superView;
    }
    private void genData() {
        eventsObjects = new ArrayList<Data>();
        for (int i = 0; i < categories.length; i++) {
            Data temp = new Data();
            temp.imageUrl = resId[i];
            temp.title = categories[i];
            eventsObjects.add(temp);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){

            case 0 :break;
            case 1:Toast.makeText(superView.getContext(),"TO be uploaded soon",Toast.LENGTH_SHORT).show();break;
            case 2: startActivity(new Intent(getActivity(), Event_List.class));break;
           // case 3:Toast.makeText(superView.getContext(),"TO be uploaded soon",Toast.LENGTH_SHORT).show();break;
            case 3:startActivity(new Intent(getActivity(), SponsersActivity.class));break;
            case 4:startActivity(new Intent(getActivity(), GalleryMainActivity.class));break;
            case 5: startActivity(new Intent(getActivity(), YtpChannel.class));break;
            case 6: startActivity(new Intent(getActivity(),TestimonialActivity.class));break;


        }
    }
}