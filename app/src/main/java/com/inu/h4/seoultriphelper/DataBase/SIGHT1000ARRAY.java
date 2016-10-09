package com.inu.h4.seoultriphelper.DataBase;

import android.util.Log;

import com.inu.h4.seoultriphelper.Home.HomeRankingListViewItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-05.
 */
public class SIGHT1000ARRAY {
    public static ArrayList<SIGHT1000_LIST> sight1000Array = new ArrayList<SIGHT1000_LIST>();

    public static void getMonthArrayData(HomeRankingListViewItem item, int i){
        //item.setRanking(sight1000Array.get(0).getData());
        item.setRanking(Integer.parseInt(sight1000Array.get(i).getData(0)));
        item.setSightName(sight1000Array.get(i).getData(1));
        Log.d("LOG/SIGHT1000ARRAYM", item.getSightName());
    }

    public static void getWeekArrayData(HomeRankingListViewItem item, int i){
        item.setRanking(Integer.parseInt(sight1000Array.get(i).getData(0)));
        item.setSightName(sight1000Array.get(i).getData(1));
        Log.d("LOG/SIGHT1000ARRAYW", item.getSightName());
    }
}