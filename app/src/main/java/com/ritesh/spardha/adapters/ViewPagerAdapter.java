package com.ritesh.spardha.adapters;

/**
 * Created by ritesh_kumar on 08-Jun-15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ritesh.spardha.home_fragments.MapsFragment;
import com.ritesh.spardha.home_fragments.Tab1;
import com.ritesh.spardha.home_fragments.Tab2;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    int FragmentPicker=0;
    Fragment[] tab3Framgents={new MapsFragment()};
    String[] tab3Titles ={"Recents","Maps"};

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    public void setFragmentPicker(int FragmentPicker){
        this.FragmentPicker=FragmentPicker;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            case 2: return tab3Framgents[FragmentPicker];

        }
        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==2){
            return tab3Titles[FragmentPicker];
        }else{
            return Titles[position];
        }

    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }


}