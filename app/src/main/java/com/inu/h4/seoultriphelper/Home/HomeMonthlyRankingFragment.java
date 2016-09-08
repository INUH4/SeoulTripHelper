package com.inu.h4.seoultriphelper.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

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
        adapter = new HomeRankingListViewAdapter();

        listView = (ListView) layout.findViewById(R.id.home_monthly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadData();
        addListViewItem();
        return layout;
    }

    public void loadData() {
        data = new ArrayList<>();
        HomeRankingListViewItem item = new HomeRankingListViewItem();
        item.setSightName("하하하");
        item.setRanking(1);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);

        item = new HomeRankingListViewItem();
        item.setSightName("호호호");
        item.setRanking(2);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);

        item = new HomeRankingListViewItem();
        item.setSightName("후후후");
        item.setRanking(3);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);
    }
    public void addListViewItem() {
        for(int i=0;i<data.size();i++) {
            adapter.addItem(data.get(i));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LOG/MONTH", "onCreate()");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("LOG/MONTH", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("LOG/MONTH", "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d("LOG/MONTH", "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LOG/MONTH", "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LOG/MONTH", "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("LOG/MONTH", "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("LOG/MONTH", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("LOG/MONTH", "onDetach()");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("LOG/MONTH", "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }
}