package com.ritesh.spardha.spardha2015;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ritesh.spardha.CustomizeGallery.GalleryMainActivity;
import com.ritesh.spardha.adapters.MyAdapter;
import com.ritesh.spardha.adapters.ViewPagerAdapter;
import com.ritesh.spardha.home_fragments.AboutUsFragment;
import com.ritesh.spardha.home_fragments.ContactFragment;
import com.ritesh.spardha.home_fragments.SpardhaHomeFragment;
import com.ritesh.spardha.home_fragments.SportsFragment;
import com.ritesh.spardha.maps.MapsActivity;
import com.ritesh.spardha.registration.RegistrationFragment;
import com.ritesh.spardha.sliding_tabs.SlidingTabLayout;


public class SpardhaHome extends AppCompatActivity {


    // Declaring Your View and Variables
    Context context;
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Upcomings", "Categories"};
    int Numboftabs = 2;
    String SPORTS_HOME_FRAGMENT_TAG="sportsHomeFragment",SPORTS_FRAGMENT_TAG="sportsFragment";
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home", "Contacts", "Get Directions", "Gallery", "Register", "Settings", "Feedback", "About Us"};
    int ICONS[] = {R.drawable.ic_home, R.drawable.ic_contacts, R.drawable.ic_maps, R.drawable.ic_gallery, R.drawable.ic_register, R.drawable.ic_settings, R.drawable.ic_feedback, R.drawable.ic_aboutus};
//    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_events,R.drawable.ic_mail,R.drawable.ic_shop,R.drawable.ic_travel};
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Ritesh Kumar";
    String EMAIL = "ritesh.kumar.ece13@iitbhu.ac.in";
    int PROFILE = R.drawable.face_rit;
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle
    SpardhaHomeFragment spardhaHomeFragment;
    FragmentManager fm;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spardha_home_activity_layout);

        context = getApplicationContext();
        fm=getSupportFragmentManager();

//        if(findViewById(R.id.container)!=null){
           /* if (savedInstanceState != null) {
                return;
            }*/
            /*spardhaHomeFragment = new SpardhaHomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, spardhaHomeFragment).commit();*/

//        }
        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE, this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        final GestureDetector mGestureDetector = new GestureDetector(SpardhaHome.this, new GestureDetector.SimpleOnGestureListener() {

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
                            transaction=fm.beginTransaction();
                            transaction.replace(R.id.container, new SpardhaHomeFragment());
                            transaction.commit();
                            //spardhaHomeFragment.pager.setCurrentItem(0, true);
                            break;
                        case 2://toolbar.setTitle("Contacts");
                            startActivity(new Intent(SpardhaHome.this, ContactActivity.class));
//                                transaction=fm.beginTransaction();
//                                transaction.replace(R.id.container, new ContactFragment());
//                                transaction.commit();
                            break;
                        case 3:
                            startActivity(new Intent(SpardhaHome.this, MapsActivity.class));
                            break;
                        case 4:
                            Intent kl = new Intent(SpardhaHome.this, GalleryMainActivity.class);
                            startActivity(kl);
                            break;
                        case 5:startActivity(new Intent(SpardhaHome.this, RegisterActivity.class));
                        /*toolbar.setTitle("Register");
                            transaction=fm.beginTransaction();
                            transaction.replace(R.id.container, new RegistrationFragment());
                            transaction.commit();*/
                            break;
                        case 6:toolbar.setTitle("Settings");
                            transaction=fm.beginTransaction();
                            transaction.replace(R.id.container, new SportsFragment());
                            transaction.addToBackStack("Settings");
                            transaction.commit();
                            Toast.makeText(context, "you clicked settings ", Toast.LENGTH_SHORT).show();
                            break;
                        case 7:toolbar.setTitle("Feedback");
                            Toast.makeText(context, "you clicked feedback ", Toast.LENGTH_SHORT).show();
                            break;
                        case 8:toolbar.setTitle("About Us");
                            transaction=fm.beginTransaction();
                            transaction.replace(R.id.container, new AboutUsFragment());
                            transaction.addToBackStack("About Us");
                            transaction.commit();
                            break;
                    }
                    //Toast.makeText(SpardhaHome.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        spardhaHomeFragment = new SpardhaHomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, spardhaHomeFragment).commit();

//        spardhaHomeFragment.allInOne(adapter);
//        spardhaHomeFragment.setViewPagerAdapter(adapter);
        /*// Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);*/
    //    spardhaHomeFragment.setTabs();
        /*// Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);*/


    }
    public void shiftToTab3(int FragmentPicker) {
        adapter.setFragmentPicker(FragmentPicker);
//        adapter.setTitle3(FragmentPicker);
        //pager.setAdapter(adapter);


        //title needs to be changed on changing 3rd tab
        adapter.notifyDataSetChanged();


        if (pager.getCurrentItem() == 2) {
            pager.setAdapter(adapter);

        }
        pager.setCurrentItem(2, true);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
