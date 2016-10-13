package com.inu.h4.seoultriphelper.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.DataBase.InsertDB_REVIEW1000;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1000ARRAY;
import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeMonthlyRankingFragment extends Fragment {
    private ListView listView;
    private ArrayList<HomeRankingListViewItem> data;
    private HomeRankingListViewAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LOG/MONTH", "onStart()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("LOG/MONTH", "onCreatedView()");
        View layout = inflater.inflate(R.layout.home_monthly_ranking, container, false);
        adapter = new HomeRankingListViewAdapter(this);

        listView = (ListView) layout.findViewById(R.id.home_monthly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getData();
        refresh();

        return layout;
    }

    public void getData(){
        data = new ArrayList<>();
        HomeRankingListViewItem item;
        SIGHT1000ARRAY.Monthsorting();
        for(int i = 0; i< SIGHT1000ARRAY.sight1000Array.size(); i++) {
            item = new HomeRankingListViewItem();
            Log.d("LOG/MONTH", "beforeGetData");
            SIGHT1000ARRAY.getMonthArrayData(item, i);
            Log.d("LOG/MONTH", "GetData : " + item.getSightName());
            data.add(item);
            adapter.addItem(data.get(i));
        }
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
        Log.d("LOG/MONTH", "Refresh");
    }
}