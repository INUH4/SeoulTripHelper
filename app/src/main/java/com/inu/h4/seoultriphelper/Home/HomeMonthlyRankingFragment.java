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

        adapter = new HomeRankingListViewAdapter();

        listView = (ListView) getActivity().findViewById(R.id.home_monthly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadData();
        addListViewItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_monthly_ranking, container, false);
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
}