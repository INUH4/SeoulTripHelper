package com.inu.h4.seoultriphelper.Prefer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.R;

public class PagePreferExistFragment extends Fragment {
    Bundle bundle = null;
    Button reexamineButton = null;
    private int preferIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.page_prefer_full, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        bundle = getArguments();
        if(bundle != null) {
            preferIndex = (int) bundle.get("preferIndex");
            getMyPrefer();
        }

        reexamineButton = (Button) getView().findViewById(R.id.btn_prefer_reexamine);
    }

    public void getMyPrefer() {

    }
}