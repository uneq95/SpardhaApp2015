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

import com.ritesh.spardha.CustomizeGallery.GalleryMainActivity;
import com.ritesh.spardha.adapters.CattestAdapter;
import com.ritesh.spardha.events.Event_List;
import com.ritesh.spardha.spardha2015.AboutSpardha;
import com.ritesh.spardha.spardha2015.R;
import com.ritesh.spardha.spardha2015.SponsersActivity;
import com.ritesh.spardha.spardha2015.TestimonialActivity;
import com.ritesh.spardha.youtube.YtpChannel;

public class Tab2 extends Fragment implements AdapterView.OnItemClickListener {

    View superView;

    private GridView mGridView;
//    String[] categories = {"ABOUT SPARDHA","INAUGRATION", "EVENTS", "INFORMALS","SPONSERS","GALLERY","YOUTUBE","TESTIMONIALS"};
    int[] resId = {R.drawable.x6,R.drawable.x4, R.drawable.x8, R.drawable.x3,R.drawable.x2,R.drawable.x5, R.drawable.x7, R.drawable.x1};
    String[] colorCodes ={"#e74c3c","#3498db","#e67e22","#9b59b6","#27ae60","#e74c3c","#2c3e50","#3498db"};
    String[] categories = {"About Spardha","Inauguration", "Events","Informals", "Sponsors", "Gallery","Youtube", "Testimonials"};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.cat_test, container, false);
       mGridView = (GridView) superView.findViewById(R.id.gvcattest);
        CattestAdapter adapter = new CattestAdapter(getActivity().getBaseContext(), categories, resId,colorCodes);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(this);

        return superView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){

            case 0 :startActivity(new Intent(getActivity(), AboutSpardha.class));break;
            case 1:Toast.makeText(superView.getContext(),"To be uploaded soon",Toast.LENGTH_SHORT).show();break;
            case 2: startActivity(new Intent(getActivity(), Event_List.class));break;
            case 3:Toast.makeText(superView.getContext(),"To be uploaded soon",Toast.LENGTH_SHORT).show();break;
            case 4:startActivity(new Intent(getActivity(), SponsersActivity.class));break;
            case 5:startActivity(new Intent(getActivity(), GalleryMainActivity.class));break;
            case 6: startActivity(new Intent(getActivity(), YtpChannel.class));break;
            case 7: startActivity(new Intent(getActivity(),TestimonialActivity.class));break;


        }
    }
}