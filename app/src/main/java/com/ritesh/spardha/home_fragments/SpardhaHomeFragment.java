package com.ritesh.spardha.home_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ritesh.spardha.adapters.ViewPagerAdapter;
import com.ritesh.spardha.sliding_tabs.SlidingTabLayout;
import com.ritesh.spardha.spardha2015.R;

/**
 * Created by ritesh_kumar on 26-Jun-15.
 */
public class SpardhaHomeFragment extends Fragment {

    View superView;
    public ViewPager pager;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Upcomings", "Categories"};
    int Numboftabs = 2;
    ViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.superView= inflater.inflate(R.layout.spardha_home_fragment,container,false);
        this.pager = (ViewPager) superView.findViewById(R.id.pager);
        this.tabs = (SlidingTabLayout) superView.findViewById(R.id.tabs);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), Titles, Numboftabs);
        // Assigning ViewPager View and setting the adapter
//        pager = (ViewPager) superView.findViewById(R.id.pager);

        pager.setAdapter(adapter);
//        this.tabs = (SlidingTabLayout) superView.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        return superView;
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

    public void allInOne(ViewPagerAdapter adapter){
//        this.pager = (ViewPager) superView.findViewById(R.id.pager);
        this.pager.setAdapter(adapter);
//        this.tabs = (SlidingTabLayout) superView.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setViewPagerAdapter(ViewPagerAdapter adapter){
        pager.setAdapter(adapter);
    }

    public void setTabs(){
        tabs = (SlidingTabLayout) superView.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }


}