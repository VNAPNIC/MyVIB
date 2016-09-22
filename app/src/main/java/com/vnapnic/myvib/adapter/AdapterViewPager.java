package com.vnapnic.myvib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnapnic on 7/7/2016.
 */
public class AdapterViewPager extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public AdapterViewPager(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void onClear() {
        fragments.clear();
    }

    public void addFragment(Fragment... fragments) {
        if (fragments.length > 0) {
            if (this.fragments == null) {
                this.fragments = new ArrayList<>();
            }
            List<Fragment> fragmentsNews = new ArrayList<>();
            for (Fragment fragment : fragments) {
                fragmentsNews.add(fragment);
            }
            this.fragments.addAll(fragmentsNews);
            notifyDataSetChanged();
        }
    }

    public int geLength() {
        if (fragments != null)
            return fragments.size();
        else
            return 0;
    }
}