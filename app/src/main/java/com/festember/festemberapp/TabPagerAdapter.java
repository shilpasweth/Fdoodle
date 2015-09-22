package com.festember.festemberapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new introFragment();
            case 4 :
                // Games fragment activity
                return new rulesFragment();
            case 3:
                // Movies fragment activity
                return new prizeFragment();

            case 2:
                return new timeFragment();

            case 1:
                return new contactFragment();


        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}