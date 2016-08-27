package com.inu.h4.seoultriphelper.Bucket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inu.h4.seoultriphelper.R;

public class PageBucketExistFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("사용자 성향");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bucket_empty, container, false);
    }
}
