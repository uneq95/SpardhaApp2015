package com.ritesh.spardha.maps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;
import com.ritesh.spardha.spardha2015.LocationActivity;
import com.ritesh.spardha.spardha2015.R;

public class MapsActivity extends AppCompatActivity {


    private GoogleMap mMap;
    float zoomlevel = (float) 14.55;

    private final String[] HOSTEL_NAME = {"Visweshwarayya","SN Bose","Aryabhatta","Ramanujan","Dhanrajgiri","Morvi","CV Raman","Rajputana","Limbdi","SC De","Vivekanand","Vishwakarma","GSMC"};
    private final double[] HOSTEL_LATITUDE = {25.262834,25.263125,25.264012,25.263124,25.263912,25.265025,25.265865,25.262418,25.261312,25.260184,25.259272,25.257690,25.260601};
    private final double[] HOSTEL_LONGITUDE = {82.983902,82.983886,82.984352,82.984826,82.986277,82.986382,82.986481,82.986349,82.986524,82.986859,82.987251,82.985695,82.983670};

    private final String[] ATM_NAME = {"SBI Hyderabad Gate","Axis Bank Hyderabad Gate","SBI-BHU", "SBI VT", "Bank of Baroda"};
    private final double[] ATM_LATITUDE = {25.261717,25.261593,25.263738, 25.265331,25.265386};
    private final double[] ATM_LONGITUDE  = {82.981647,82.981642,82.994762,82.989635,82.989643};

    private final String[] VENUE_NAME = {"IIT-BHU Gymkhana","Rajputana Ground","Amphitheatre","Arun Dream Village(ADV)"};
    private final double[] VENUE_LATITUDE = {25.259176,25.262499,25.265730,25.259040};
    private final double[] VENUE_LONGITUDE = {82.989229,82.986691,82.995231,82989331};

    private final String[] HOSPITAL_NAME = {"Sir Sunderlal Hospital"};
    private final double[] HOSPITAL_LATITUDE = {25.275727};
    private final double[] HOSPITAL_LONGITUDE = {83.000570};

    private final String[] OTHER_NAMES = {"DG Corner","Limbdi Corner","Vishvanath Temple","Swatantrata Bhawan"};
    private final double[] OTHER_LATITUDE = {25.263023,25.260777,25.265676,25.260636};
    private final double[] OTHER_LONGITUDE = {82.986498,82.986884,82.989475,82.993676};
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Toast.makeText(this,"Click on upper-right corner to navigate",Toast.LENGTH_SHORT).show();
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)){
            new GCMStarter(this).GCMEnable();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setUpMapIfNeeded();
        addMarker(HOSTEL_LATITUDE, HOSTEL_LONGITUDE, HOSTEL_NAME);

        final FloatingActionsMenu fam = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

        final FloatingActionButton button_hospital = (FloatingActionButton) findViewById(R.id.button_hospital);
        button_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(HOSPITAL_LATITUDE, HOSPITAL_LONGITUDE, HOSPITAL_NAME);
                fam.collapse();
            }
        });

        final FloatingActionButton button_venue = (FloatingActionButton) findViewById(R.id.button_venue);
        button_venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(VENUE_LATITUDE, VENUE_LONGITUDE, VENUE_NAME);
                fam.collapse();

            }
        });

        final FloatingActionButton button_atm = (FloatingActionButton) findViewById(R.id.button_atm);
        button_atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(ATM_LATITUDE, ATM_LONGITUDE, ATM_NAME);
                fam.collapse();

            }
        });

        final FloatingActionButton button_hostel = (FloatingActionButton) findViewById(R.id.button_hostel);
        button_hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(HOSTEL_LATITUDE, HOSTEL_LONGITUDE, HOSTEL_NAME);
                fam.collapse();

            }
        });
        final FloatingActionButton button_miscellaneous = (FloatingActionButton) findViewById(R.id.button_miscellaneous);
        button_miscellaneous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                addMarker(OTHER_LATITUDE, OTHER_LONGITUDE, OTHER_NAMES);
                fam.collapse();

            }
        });


        // MAP sETTINGS
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setScrollGesturesEnabled(true);
        mapSettings.setTiltGesturesEnabled(true);
        mapSettings.setRotateGesturesEnabled(true);
        mapSettings.setMyLocationButtonEnabled(true);
        Toolbar toolbar =(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        /** To Get Current Location **/
        mMap.setMyLocationEnabled(true);
            /*LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location myLocation = locationManager.getLastKnownLocation(provider);
            double latitude = myLocation.getLatitude();
            double longitude = myLocation.getLongitude();*/
        LatLng latLng = new LatLng(25.265834, 82.989499);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(zoomlevel));


    }

    public void addMarker(double[] Latitude,double[] Longitude, String[] Title){

        for(int i = 0; i < Latitude.length;i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(Latitude[i], Longitude[i])).title(Title[i]));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_navigate) {

            Intent i = new Intent(MapsActivity.this, LocationActivity.class);
            startActivity(i);
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
