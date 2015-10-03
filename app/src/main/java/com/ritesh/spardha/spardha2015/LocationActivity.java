package com.ritesh.spardha.spardha2015;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;
import com.ritesh.spardha.location.IITPlacesOfInterest;
import com.ritesh.spardha.location.SinglePlaceLocation;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 17-May-15.
 */
public class LocationActivity extends AppCompatActivity {

    IITPlacesOfInterest iitPlacesOfInterest;
    ArrayList<SinglePlaceLocation> IITPlaces,startingLocationsListWRTCategory,destinationLocationListWRTCategory;
    Spinner spinnerFromPlaceCategory, spinnerFromPlaceName, spinnerToPlaceCategory, spinnerToPlaceName;
    String[] Category;
    boolean[] spinnerCheck = {false, false, false, false};
    SinglePlaceLocation destination, startingPoint;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iit_bhu_navigator_layout);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)){
            new GCMStarter(this).GCMEnable();
        }
        loadLocationData();
        initViews();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadLocationLists();
        setCategorySpinners();
        setLocationSpinners();

        
    }

    public void loadLocationData() {
        iitPlacesOfInterest = new IITPlacesOfInterest();
        IITPlaces = iitPlacesOfInterest.getIITPlaces();
       // System.out.println("location data loaded");
    }

    public void initViews() {
        spinnerFromPlaceCategory = (Spinner) findViewById(R.id.spinnerFromPlaceCategory);
        spinnerFromPlaceName = (Spinner) findViewById(R.id.spinnerFromPlaceName);
        spinnerToPlaceCategory = (Spinner) findViewById(R.id.spinnerToPlaceCategory);
        spinnerToPlaceName = (Spinner) findViewById(R.id.spinnerToPlaceName);
        //System.out.println("spinners initialised");
    }

    public void loadLocationLists() {
        Category = IITPlacesOfInterest.Category;System.out.println("location list loaded");
    }

    public void setCategorySpinners() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromPlaceCategory.setAdapter(dataAdapter);
        spinnerToPlaceCategory.setAdapter(dataAdapter);
       // System.out.println("category adapters set");
        spinnerFromPlaceCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                destinationLocationListWRTCategory = filterLocationsWRTCategory(position);
                startingLocationsListWRTCategory = filterLocationsWRTCategory(position);
                ArrayList<String> locationNameListWRTCategory = retrieveLocationNameListWRTCategory(startingLocationsListWRTCategory);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, locationNameListWRTCategory);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerFromPlaceName.setAdapter(dataAdapter);
                spinnerCheck[2] = true;
                //System.out.println("inside from place category listener");
                //System.out.println("starting locations list: "+ locationNameListWRTCategory);
                /*for(int i=0;i<locationNameListWRTCategory.size();i++){
                    syso(locationNameListWRTCategory.get(i));
                }
*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerCheck[2] = false;
                Toast.makeText(getBaseContext(), "Please choose a category for the list of locations!", Toast.LENGTH_SHORT).show();
            }
        });
        spinnerToPlaceCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinationLocationListWRTCategory= filterLocationsWRTCategory(position);
                ArrayList<String> locationNameListWRTCategory = retrieveLocationNameListWRTCategory(destinationLocationListWRTCategory);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, locationNameListWRTCategory);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToPlaceName.setAdapter(dataAdapter);
                spinnerCheck[3] = true;
                //System.out.println("inside to place category listener");
                //System.out.println("destination locations list: "+ locationNameListWRTCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerCheck[3] = false;
                Toast.makeText(getBaseContext(), "Please choose a category for the list of locations!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //public void syso(String msg){System.out.println(msg);}
    public void setLocationSpinners() {

        spinnerFromPlaceName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerCheck[0] = true;
                //startingPoint = (SinglePlaceLocation) parent.getAdapter().getItem(position);
                startingPoint=startingLocationsListWRTCategory.get(position);
                //System.out.println("selected sp location:" +parent.getAdapter().getItem(position));
                //System.out.println("coded sp location:" +startingPoint.getPlaceName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerCheck[0] = false;
            }
        });

        spinnerToPlaceName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerCheck[1] = true;
                //destination = (SinglePlaceLocation) parent.getAdapter().getItem(position);
                destination=destinationLocationListWRTCategory.get(position);
               // System.out.println("selected dp location:" +(parent.getAdapter().getItem(position)));
               // System.out.println("coded dp location:" +destination.getPlaceName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerCheck[1] = false;
            }
        });
    }

    public ArrayList<SinglePlaceLocation> filterLocationsWRTCategory(int category) {
        ArrayList<SinglePlaceLocation> locationsListWRTCategory = new ArrayList<>();
        for (int i = 0; i < IITPlaces.size(); i++) {
            if (IITPlaces.get(i).getPlaceCategory() == category) {
                locationsListWRTCategory.add(IITPlaces.get(i));
               // System.out.println("category: "+IITPlacesOfInterest.Category[category] );
                //System.out.println("iit place: "+ IITPlaces.get(i).getPlaceName());

            }
        }
        return locationsListWRTCategory;
    }

    public ArrayList<String> retrieveLocationNameListWRTCategory(ArrayList<SinglePlaceLocation> locationsListWRTCategory) {
        ArrayList<String> locationNameListWRTCategory = new ArrayList<>();
        for (int i = 0; i < locationsListWRTCategory.size(); i++) {
            locationNameListWRTCategory.add(locationsListWRTCategory.get(i).getPlaceName());
        }
        return locationNameListWRTCategory;
    }

    public void onClickNavigate(View v) {

        if (spinnerCheck[0] && spinnerCheck[1] && spinnerCheck[2] && spinnerCheck[3]) {
            String sLong = startingPoint.getPlaceLongitude(), sLat = startingPoint.getPlaceLatitude(), dLong = destination.getPlaceLongitude(), dLat = destination.getPlaceLatitude();
            Uri gmmIntentUri = Uri.parse(String.format("http://maps.google.com/maps?saddr=%s,%s&daddr=%s,%s", sLong, sLat, dLong, dLat));
            //Uri.parse("http://maps.google.com/maps?saddr="+src_lat+","+src_ltg+"&daddr="+des_lat+","+des_ltg))
            //Uri gmmIntentUri = Uri.parse("google.navigation:q=India+Gate,+Delhi+India");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        } else {
            Toast.makeText(getBaseContext(), "Please set all fields properly and try again!", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
