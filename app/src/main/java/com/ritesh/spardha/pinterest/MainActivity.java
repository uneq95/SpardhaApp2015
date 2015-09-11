package com.ritesh.spardha.pinterest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.ritesh.spardha.spardha2015.R;

import java.util.ArrayList;

public class MainActivity extends Activity implements AbsListView.OnItemClickListener {

    private StaggeredGridView mGridView;
    private DataAdapter mAdapter;
    ArrayList<Data> eventsObjects;
    String[] categories = {"Inaugration", "Events", "Informals", "Sponsers", "Gallery", "Closing", "Testimonials"};
    int[] resId = {R.drawable.inauguration, R.drawable.informal, R.drawable.informal, R.drawable.sponsors, R.drawable.gallery, R.drawable.clsoing_ceremony, R.drawable.clsoing_ceremony};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinterst_cat);

        //setTitle("Pinterest Layout Demo");
        mGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        genData();

        mAdapter = new DataAdapter(this, R.layout.list_item_sample, eventsObjects);

        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
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
}
