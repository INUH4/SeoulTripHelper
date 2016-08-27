package com.inu.h4.seoultriphelper.Planner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.R;

public class PagePlannerExistFragment extends Fragment {
    Button tempButton;
    @Override
    public void onStart() {
        super.onStart();

        tempButton = (Button) getView().findViewById(R.id.btn_temp);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PagePlannerDetailFragment()).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("여행 플래너");
        return inflater.inflate(R.layout.page_planner, container, false);
    }
}
