package com.ishaan.basicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class Tabact extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabact);
        tl = (TabLayout) findViewById(R.id.tl);
        vp = (ViewPager) findViewById(R.id.vp);

        tl.addTab(tl.newTab().setText("Restaurant List"));
        tl.addTab(tl.newTab().setText("Restaurant Request"));
        tl.setTabGravity(TabLayout.GRAVITY_FILL);

    final iad ad = new iad(this, getSupportFragmentManager(), tl.getTabCount());
    vp.setAdapter(ad);

    vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tl));
    tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            vp.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    });
    }
}
