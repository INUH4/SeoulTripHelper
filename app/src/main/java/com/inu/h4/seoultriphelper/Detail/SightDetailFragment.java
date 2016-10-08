package com.inu.h4.seoultriphelper.Detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.Home.HomeRankingListViewAdapter;
import com.inu.h4.seoultriphelper.Home.HomeRankingListViewItem;
import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

public class SightDetailFragment extends Fragment {
    private GridView gridView;
    private ArrayList<SightDetailGridViewItem> data;
    private SightDetailGridViewAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.detail, container, false);

        adapter = new SightDetailGridViewAdapter();

        gridView = (GridView) layout.findViewById(R.id.detail_list);
        gridView.setAdapter(adapter);
        gridView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadData();
        addListViewItem();
        return layout;
    }

    public void loadData() {
        data = new ArrayList<>();
        SightDetailGridViewItem item = new SightDetailGridViewItem();
        item.setImage(R.drawable.page_search_icon);
        data.add(item);

        item = new SightDetailGridViewItem();
        item.setImage(R.drawable.page_bucket_icon);
        data.add(item);

        item = new SightDetailGridViewItem();
        item.setImage(R.drawable.page_setting_icon);
        data.add(item);
    }
    public void addListViewItem() {
        for(int i=0;i<data.size();i++) {
            adapter.addItem(data.get(i));
        }
    }
}
