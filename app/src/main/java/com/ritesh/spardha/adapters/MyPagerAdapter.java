package com.ritesh.spardha.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.ritesh.spardha.home_fragments.FragmentCategories;
import com.ritesh.spardha.home_fragments.FragmentUpcomingEvents;
import com.ritesh.spardha.home_fragments.MyFragment;
import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 11-May-15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final String[] TITLES = {"Upcoming Events", "Categories"};
    FragmentManager fragmentManager;

    public MyPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.fragmentManager = fm;
        this.context = c;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment newFragment = new FragmentUpcomingEvents();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                transaction.add(newFragment, "Upcoming events");
                transaction.commit();
                break;
            case 1:
                newFragment = new FragmentCategories();
                transaction.add(newFragment, "categories");
                transaction.commit();
                break;
        }
        return MyFragment.newInstance(position);
    }
}
