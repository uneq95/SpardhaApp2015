package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.ritesh.spardha.Utils.StorageDirs;
import com.ritesh.spardha.adapters.SponserListAdapter;
import com.ritesh.spardha.sponsers.Sponser;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 16-Aug-15.
 */
public class SponsersActivity extends AppCompatActivity {


    ListView lvSponserList;
    SponserListAdapter adapter;
    ArrayList<Sponser> sponserArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsers_activity);
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(StorageDirs.spardhaRoot, Context.MODE_PRIVATE);
        File oldSponsersFolder=new File(directory+File.separator+StorageDirs.prevSponser);
        File newSponsers= new File(directory+File.separator+StorageDirs.newSponser);
        if(newSponsers.listFiles().length!=0){


        }

    }

}
