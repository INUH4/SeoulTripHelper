package com.inu.h4.seoultriphelper.Planner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

public class PlannerExistFragment extends Fragment {

    PlannerDB plannerDB;
    private ListView listView;
    private ArrayList<PlannerListViewItem> data;
    private PlannerListViewAdapter adapter;

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        plannerDB = new PlannerDB(getActivity(), "planner.db", null, 1);
        View layout = inflater.inflate(R.layout.planner, container, false);
        adapter = new PlannerListViewAdapter(this, plannerDB);

        listView = (ListView) layout.findViewById(R.id.PlannerListView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getData();
        refresh();

        return layout;
    }

    public void getData(){
        plannerDB = new PlannerDB(getActivity(), "planner.db", null, 1);
        plannerDB.DBSelect(data, adapter);
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
    }
}
