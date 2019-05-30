package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BookmarkPagerAdapter extends FragmentPagerAdapter {
    public BookmarkPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new TheoDoiTabFragment();
            case 1:
                return new XemGanDayTabFragment();
                default:
                    return new TheoDoiTabFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

