package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ritesh.spardha.adapters.SponserListAdapter;
import com.ritesh.spardha.sponsers.Sponser;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 16-Aug-15.
 */
public class SponsersActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsers_activity);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ListView lv = (ListView) findViewById(R.id.lvSponsers);
//        String[] sponsers = this.getResources().getStringArray(R.array.sponsers);
        String[] sponsers ={
                "http://www.spardha.co.in/img/sponsors/hp.png",
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
        SponserListAdapter adapter = new SponserListAdapter(sponsers,this);
        lv.setAdapter(adapter);
        /*ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(StorageDirs.spardhaRoot, Context.MODE_PRIVATE);
        File oldSponsersFolder=new File(directory+File.separator+StorageDirs.prevSponser);
        File newSponsers= new File(directory+File.separator+StorageDirs.newSponser);
        if(newSponsers.listFiles().length!=0){
        }*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
