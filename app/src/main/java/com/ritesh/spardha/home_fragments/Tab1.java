package com.ritesh.spardha.home_fragments;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ritesh.spardha.adapters.GcmUpdatesListAdapter;
import com.ritesh.spardha.gcm.GcmMessage;
import com.ritesh.spardha.gcm.GcmMessageQueueDatabase;
import com.ritesh.spardha.spardha2015.R;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    View superView;
    ListView gcmMsgList;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        superView = inflater.inflate(R.layout.upcomings_layout, container, false);
        context = getActivity().getBaseContext();
        GcmMessageQueueDatabase db = new GcmMessageQueueDatabase(context);
        db.open();
        ArrayList<GcmMessage> gcmMessages = db.fetchAllGcmMessages();
        System.out.println("msg size: " + gcmMessages.size());
        db.close();
        gcmMsgList = (ListView) superView.findViewById(R.id.lvUpcomings);
        GcmUpdatesListAdapter adapter = new GcmUpdatesListAdapter(getActivity(), gcmMessages);
        syso("initialised adapter");
        gcmMsgList.setAdapter(adapter);
        syso("adapter set");
        return superView;
    }
    private void syso(String msg){
        System.out.println(msg);
    }
}
