package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.ritesh.spardha.CustomizeGallery.GalleryMainActivity;
import com.ritesh.spardha.adapters.MyAdapter;
import com.ritesh.spardha.adapters.ViewPagerAdapter;
import com.ritesh.spardha.gcm.GCMStarter;
import com.ritesh.spardha.gcm.QuickstartPreferences;
import com.ritesh.spardha.maps.MapsActivity;
import com.ritesh.spardha.sliding_tabs.SlidingTabLayout;

/**
 * Created by ritesh_kumar on 12-Aug-15.
 */
public class SpardhaHomeTest extends AppCompatActivity {
    // Declaring Your View and Variables
    Context context;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Upcoming", "Categories"};
    int Numboftabs = 2;
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see
    String TITLES[] = {"Register", "Campus Ambassador", "Contacts", "Get Directions", "Gallery", "Feedback", "About Us"};
    int ICONS[] = {R.drawable.ic_register, R.drawable.ic_campus_ambassador, R.drawable.ic_contacts, R.drawable.ic_maps, R.drawable.ic_gallery, R.drawable.ic_feedback, R.drawable.ic_aboutus};
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saprdhahometest);
        context = getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false)) {
            new GCMStarter(this).GCMEnable();
        }
        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setElevation(10);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        final GestureDetector mGestureDetector = new GestureDetector(SpardhaHomeTest.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Drawer.closeDrawers();
                    switch (recyclerView.getChildPosition(child)) {
                    case 1:
                        startActivity(new Intent(SpardhaHomeTest.this, RegisterActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SpardhaHomeTest.this, CampusAmbassadorAct.class));
                        break;
                    case 3:
                        startActivity(new Intent(SpardhaHomeTest.this, ContactActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(SpardhaHomeTest.this, MapsActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(SpardhaHomeTest.this, GalleryMainActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(SpardhaHomeTest.this, FeedbackActivty.class));
                        break;
                    case 7:
                        startActivity(new Intent(SpardhaHomeTest.this, AboutUsActivity.class));
                        break;

                }
                return true;

            }

            return false;
        }

        @Override
        public void onTouchEvent (RecyclerView recyclerView, MotionEvent motionEvent){

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent ( boolean disallowIntercept){

        }
    }

    );


    mLayoutManager=new LinearLayoutManager(this);                 // Creating a layout Manager

    mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


    Drawer=(DrawerLayout)

    findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view

    mDrawerToggle=new

    ActionBarDrawerToggle(this,Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

        @Override
        public void onDrawerOpened (View drawerView){
            super.onDrawerOpened(drawerView);
            // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
            // open I am not going to put anything here)
        }

        @Override
        public void onDrawerClosed (View drawerView){
            super.onDrawerClosed(drawerView);
            // Code here will execute once drawer is closed
        }


    }

    ; // Drawer Toggle Object Made
    Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
    mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

    // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
    adapter=new

    ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs

    );

    // Assigning ViewPager View and setting the adapter
    pager=(ViewPager)

    findViewById(R.id.pager);

    pager.setAdapter(adapter);

    // Assiging the Sliding Tab Layout View
    tabs=(SlidingTabLayout)

    findViewById(R.id.tabs);

    tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
    tabs.setSelected(true);
    //tabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
    tabs.setSelectedIndicatorColors(R.color.white);
    // Setting Custom Color for the Scroll bar indicator of the Tab View
    tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer()

    {
        @Override
        public int getIndicatorColor ( int position){
        return getResources().getColor(R.color.tabsScrollColor);
    }
    }

    );
    tabs.setSoundEffectsEnabled(true);
    tabs.setSmoothScrollingEnabled(true);
    // Setting the ViewPager For the SlidingTabsLayout
    tabs.setViewPager(pager);


}

}
