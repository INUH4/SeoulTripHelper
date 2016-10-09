package com.inu.h4.seoultriphelper.Bucket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.inu.h4.seoultriphelper.SearchFragment;
import com.inu.h4.seoultriphelper.R;


import java.util.ArrayList;

public class BucketExistFragment extends Fragment {
    private GridView GridView;
    private ArrayList<BucketGridViewItem> data;
    private BucketGridViewAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("버킷리스트");

        View layout = inflater.inflate(R.layout.bucket_list, container, false);
        adapter = new BucketGridViewAdapter();

        GridView = (GridView) layout.findViewById(R.id.bucket_list_grid_view);
        GridView.setAdapter(adapter);
        GridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        //클릭하면 상세페이지로 넘어가게
        GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SearchFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
//        /////////////////////////////////////////////////////////////////////////////
//        MapView mapView = new MapView(getActivity());
//        mapView.setDaumMapApiKey("753615f093d763e50b6a87a0a0f25f05");
//
//        RelativeLayout BucketMapViewContainer = (RelativeLayout) layout.findViewById(R.id.bucket_list_mapView);
//        BucketMapViewContainer.addView(mapView);



        loadData();
        addListViewItem();
        return layout;
    }

    public void loadData() {

        data = new ArrayList<>();
        BucketGridViewItem item = new BucketGridViewItem();
        item.setSightName("경복궁");
        item.setRecommend(1);
        item.setImage(R.drawable.page_search_icon);
        item.setCoordinate_x(37.5796212);
        item.setCoordinate_y(126.9748523);
        data.add(item);

        item = new BucketGridViewItem();
        item.setSightName("남산타워");
        item.setRecommend(2);
        item.setImage(R.drawable.page_search_icon);
        item.setCoordinate_x(37.5511736);
        item.setCoordinate_y(126.9860379);
        data.add(item);

        item = new BucketGridViewItem();
        item.setSightName("63빌딩");
        item.setRecommend(3);
        item.setCoordinate_x(37.5193818);
        item.setCoordinate_y(126.9380216);
        item.setImage(R.drawable.page_search_icon);


        data.add(item);

    }
    public void addListViewItem() {
        for(int i=0;i<data.size();i++) {
            adapter.addItem(data.get(i));
        }
    }

}
