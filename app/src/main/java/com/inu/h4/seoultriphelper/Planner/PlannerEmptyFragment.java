package com.inu.h4.seoultriphelper.Planner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.Bucket.BucketEmptyFragment;
import com.inu.h4.seoultriphelper.Bucket.BucketExistFragment;
import com.inu.h4.seoultriphelper.Search.SearchFragment;
import com.inu.h4.seoultriphelper.R;

public class PlannerEmptyFragment extends Fragment {
    Button SubmitBucketButton,SubmitSearchButton;
    @Override
    public void onStart() {
        super.onStart();

        SubmitBucketButton = (Button) getView().findViewById(R.id.btn_submit_bucket);
        SubmitBucketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                if (true) {              // 장바구니가 비어있을 경우
                    fragment = new BucketEmptyFragment();
                } else {                // 장바구니가 비어있지 않을 경우
                    fragment = new BucketExistFragment();
                }
                if (fragment != null)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            }
        });

        SubmitSearchButton = (Button) getView().findViewById(R.id.btn_submit_search);
        SubmitSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).addToBackStack(null).commit();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("여행 플래너");
        return inflater.inflate(R.layout.planner_empty, container, false);
    }
}
