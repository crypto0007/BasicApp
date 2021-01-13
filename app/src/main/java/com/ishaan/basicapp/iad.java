package com.ishaan.basicapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class iad extends FragmentPagerAdapter {

        private Context ct;
        int tottab;


    public iad(Context ct, FragmentManager fm, int tottab) {
        super(fm);
        ct = ct;
        this.tottab = tottab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                tab0 tb0 = new tab0();
                return tb0;
            case 1:
                tab1 tb1 = new tab1();
                return tb1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tottab;
    }
}
