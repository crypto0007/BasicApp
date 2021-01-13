package com.ishaan.basicapp;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class tab0 extends Fragment {
    ListView l;
    String[] values = new String[]{"Dialog activity", "Tab Activity", "Send SMS", "Call"};

    public tab0() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_tab0, container, false);
    }
}