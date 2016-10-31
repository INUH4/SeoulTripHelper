package com.inu.h4.seoultriphelper.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.Detail.SightDetailFragment;
import com.inu.h4.seoultriphelper.InnerDBHelper;
import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;


public class HomeRankingListViewAdapter extends BaseAdapter {
    Fragment fragment;
    public HomeRankingListViewAdapter(Fragment fragment) {
        this.fragment = fragment;
    }
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<HomeRankingListViewItem> listViewItemList = new ArrayList<>() ;

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //디비호출
        final InnerDBHelper dbHelper = new InnerDBHelper(context, "BUCKETDB.db",null,1);


        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_item, parent, false);
        }


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final HomeRankingListViewItem listViewItem = listViewItemList.get(position);

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView ranking = (TextView) convertView.findViewById(R.id.home_ranking);
        TextView sightName = (TextView) convertView.findViewById(R.id.home_sight_name);
        ImageView sightImage = (ImageView) convertView.findViewById(R.id.home_sight_image);
        Button getBucketButton = (Button) convertView.findViewById(R.id.btn_home_get_bucket);
        Button recommendButton = (Button) convertView.findViewById(R.id.btn_home_recommend);
        Button moreInfoButton = (Button) convertView.findViewById(R.id.btn_home_more_info);

        //담기 버튼
        getBucketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });
        sightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment sightDetailFragment = new SightDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("placeId", listViewItem.getPlaceid());
                sightDetailFragment.setArguments(bundle);
                fragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, sightDetailFragment).addToBackStack("page_home").commit();
            }
        });
        // 아이템 내 각 위젯에 데이터 반영
        //ranking.setText(String.valueOf(listViewItem.getRanking()+"위"));
        ranking.setText(listViewItem.getRanking()+"위");
        sightName.setText(listViewItem.getSightName());
        sightImage.setImageResource(listViewItem.getImage());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(HomeRankingListViewItem item) {
        listViewItemList.add(item);
    }
}