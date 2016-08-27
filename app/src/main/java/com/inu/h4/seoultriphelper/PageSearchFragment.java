package com.inu.h4.seoultriphelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PageSearchFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_search, container, false);
    }
}