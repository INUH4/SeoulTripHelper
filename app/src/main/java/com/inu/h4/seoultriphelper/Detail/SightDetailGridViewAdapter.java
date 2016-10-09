package com.inu.h4.seoultriphelper.Detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.Detail.SightDetailFragment;
import com.inu.h4.seoultriphelper.R;
import java.util.ArrayList;


public class SightDetailGridViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<SightDetailGridViewItem> listViewItemList = new ArrayList<>();

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

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detail_image_item, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.detail_image);
        SightDetailGridViewItem item = listViewItemList.get(pos);
        imageView.setImageResource(item.getImage());          // 이미지 파일 변경.


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

    public void addItem(SightDetailGridViewItem item) {
        listViewItemList.add(item);
    }
}
