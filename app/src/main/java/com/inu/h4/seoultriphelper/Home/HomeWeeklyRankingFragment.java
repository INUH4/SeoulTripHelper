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

public class HomeWeeklyRankingFragment extends Fragment {
    private ListView listView;
    private ArrayList<HomeRankingListViewItem> data;
    private HomeRankingListViewAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LOG/WEEK", "onStart()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("LOG/WEEK", "onCreateView()");
        View layout = inflater.inflate(R.layout.home_weekly_ranking, container, false);
        adapter = new HomeRankingListViewAdapter();

        listView = (ListView) layout.findViewById(R.id.home_weekly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadData();
        addListViewItem();
        return layout;
    }

    public void loadData() {
        data = new ArrayList<>();

        HomeRankingListViewItem item = new HomeRankingListViewItem();
        item.setSightName("감자");
        item.setPlaceid(9999);
        item.setRanking(1);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);

        item = new HomeRankingListViewItem();
        item.setSightName("고구마");
        item.setPlaceid(8888);
        item.setRanking(2);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);

        item = new HomeRankingListViewItem();
        item.setSightName("럭셔리");
        item.setPlaceid(7777);
        item.setRanking(3);
        item.setImage(R.drawable.page_search_icon);
        data.add(item);
    }
    public void addListViewItem() {
        for(int i=0;i<data.size();i++) {
            adapter.addItem(data.get(i));
        }
    }
}