package com.inu.h4.seoultriphelper.Planner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.R;

public class PlannerDetailFragment extends Fragment {
    Button planSubmitCancelButton;
    @Override
    public void onStart() {
        super.onStart();

        planSubmitCancelButton = (Button) getView().findViewById(R.id.btn_plan_submit_cancel);
        planSubmitCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("여행 플래너");
        return inflater.inflate(R.layout.planner_submit_layout, container, false);
    }
}
