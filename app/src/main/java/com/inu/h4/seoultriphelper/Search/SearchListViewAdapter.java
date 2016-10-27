package com.inu.h4.seoultriphelper.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIN on 2016-10-27.
 */

public class SearchListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<SearchListViewItem> SearchListViewItemList = null;
    private ArrayList<SearchListViewItem> arrayList;

    public SearchListViewAdapter(Context context, List<SearchListViewItem> SearchListViewItemList) {
        this.context = context;
        this.SearchListViewItemList = SearchListViewItemList;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<SearchListViewItem>();
        this.arrayList.addAll(SearchListViewItemList);
    }

    public class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
    }

    @Override
    public int getCount() {
        return SearchListViewItemList.size();
    }

    @Override
    public SearchListViewItem getItem(int position) {
        return SearchListViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        final SearchListViewItem SearchListViewItem = SearchListViewItemList.get(position);

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_item_listview, null);
            // Locate the TextViews in listview_item.xml
            holder.iv_icon = (ImageView) view.findViewById(R.id.search_image);
            holder.tv_name = (TextView) view.findViewById(R.id.search_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.tv_name.setText(SearchListViewItem.getName());
        //Glide.with(context).load(SearchListViewItem.getImage()).into(holder.iv_icon);

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO : 상세보기로 넘기기.
            }
        });

        return view;
    }

    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        SearchListViewItemList.clear();
//        if (charText.length() == 0) {
//            SearchListViewItemList.addAll(arrayList);
//        } else {
//            for (SearchListViewItem SearchListViewItem : arrayList) {
//                String name = context.getResources().getString(SearchListViewItem.getName());
//                if (name.toLowerCase().contains(charText)) {
//                    SearchListViewItemList.add(SearchListViewItem);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}