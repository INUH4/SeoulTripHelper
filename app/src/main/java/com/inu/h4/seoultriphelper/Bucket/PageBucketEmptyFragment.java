package com.inu.h4.seoultriphelper.Bucket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.PageSearchFragment;
import com.inu.h4.seoultriphelper.R;

public class PageBucketEmptyFragment extends Fragment {
    Button AddBucketWithSearchButton;
    @Override
    public void onStart() {
        super.onStart();

        AddBucketWithSearchButton = (Button) getView().findViewById(R.id.bt_bucket);
        AddBucketWithSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PageSearchFragment()).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("버킷리스트");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bucket_empty, container, false);
    }
}
