package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ComicDetailPagerAdapter extends FragmentPagerAdapter {
    private Comic comic;
    public ComicDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        switch (i){
            case 1:
                bundle.putSerializable("data", comic.getArrChuong());
                bundle.putInt("comic_id", comic.getId());
                MucLucTabFragment mucLucTabFragment = new MucLucTabFragment();
                mucLucTabFragment.setArguments(bundle);
                return mucLucTabFragment;
            case 0:
            default:
                bundle.putString("data", comic.getTomTat());

                NoiDungTabFragment noiDungTabFragment = new NoiDungTabFragment();
                noiDungTabFragment.setArguments(bundle);
                return noiDungTabFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}

