package com.ritesh.spardha.spardha2015;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ritesh.spardha.adapters.CategoriesGridAdapter;
import com.ritesh.spardha.location.IITPlacesOfInterest;
import com.ritesh.spardha.location.SinglePlaceLocation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ritesh_kumar on 17-May-15.
 */
public class LocationActivity extends Activity {

    IITPlacesOfInterest locations;
    HashMap<String, SinglePlaceLocation> IITPlaces;
    Spinner spinnerFromPlaceCategory, spinnerFromPlaceName, spinnerToPlaceCategory, spinnerToPlaceName;
    String[] Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocationData();
        initViews();
        loadLocationLists();
        setCategorySpinners();
    }

    public void loadLocationData() {
        locations = new IITPlacesOfInterest();
        IITPlaces = locations.getIITPlaces();
    }

    public void initViews() {
        spinnerFromPlaceCategory = (Spinner) findViewById(R.id.spinnerFromPlaceCategory);
        spinnerFromPlaceName = (Spinner) findViewById(R.id.spinnerFromPlaceName);
        spinnerToPlaceCategory = (Spinner) findViewById(R.id.spinnerToPlaceCategory);
        spinnerToPlaceName = (Spinner) findViewById(R.id.spinnerToPlaceName);
    }

    public void loadLocationLists() {
        Category = (String[]) IITPlaces.entrySet().toArray();
    }

    public void setCategorySpinners() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromPlaceCategory.setAdapter(dataAdapter);
        spinnerToPlaceCategory.setAdapter(dataAdapter);
        spinnerFromPlaceCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categorySelected = Category[position];
                ArrayList<SinglePlaceLocation> locationsListWRTCategory = filterLocationsWRTCategory(categorySelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerToPlaceCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categorySelected = Category[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public ArrayList<SinglePlaceLocation> filterLocationsWRTCategory(String category) {
        ArrayList<SinglePlaceLocation> locationsListWRTCategory = new ArrayList<SinglePlaceLocation>();

        return locationsListWRTCategory;
    }

}
