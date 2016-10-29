package com.inu.h4.seoultriphelper.DataBase;

import android.graphics.Bitmap;

import com.inu.h4.seoultriphelper.Home.HomeMonthlyRankingFragment;
import com.inu.h4.seoultriphelper.Home.HomeRankingListViewItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2016-10-05.
 */
public class SIGHT1000ARRAY {
    public static ArrayList<SIGHT1000_LIST> sight1000Array = new ArrayList<SIGHT1000_LIST>();

    public static void getWeekArrayData(HomeRankingListViewItem item, int i){
        item.setPlaceid(Integer.parseInt(sight1000Array.get(i).getData(0)));
        item.setSightName(sight1000Array.get(i).getData(1));
        item.setWeek_recommend(Integer.parseInt(sight1000Array.get(i).getData(6)));
        item.setRanking(i+1);
        item.setRecommendCount(Integer.valueOf(sight1000Array.get(i).getData(3)));
        //SelectDB_REVIEW1000.setAvgRecommendPoint(String.valueOf(item.getPlaceid()), item);

        //Log.d("LOG/SIGHT1000ARRAYW", sight1000Array.get(i).getData(6));
    }

    public static void Weeksorting(){
        WeekComparator comp = new WeekComparator();
        Collections.sort(sight1000Array, comp);
    }

    static class WeekComparator implements Comparator<SIGHT1000_LIST> {

        @Override
        public int compare(SIGHT1000_LIST first, SIGHT1000_LIST second) {
            double firstValue = Double.parseDouble(first.getData(6));
            double secondValue = Double.parseDouble(second.getData(6));

            if (firstValue > secondValue)
                return -1;
            else if (firstValue < secondValue)
                return 1;
            else
                return 0;
        }
    }

    public static void getMonthArrayData(HomeRankingListViewItem item, int i){
        item.setPlaceid(Integer.parseInt(sight1000Array.get(i).getData(0)));
        item.setSightName(sight1000Array.get(i).getData(1));
        item.setMonth_recommend(Integer.parseInt(sight1000Array.get(i).getData(7)));
        item.setRanking(i+1);
        item.setRecommendCount(Integer.valueOf(sight1000Array.get(i).getData(3)));
        //SelectDB_REVIEW1000.setAvgRecommendPoint(String.valueOf(item.getPlaceid()), item);

        //Log.d("LOG/SIGHT1000ARRAYM", item.getSightName());
    }

    public static void Monthsorting(){
        MonthComparator comp = new MonthComparator();
        Collections.sort(sight1000Array, comp);
    }

    static class MonthComparator implements Comparator<SIGHT1000_LIST> {

        @Override
        public int compare(SIGHT1000_LIST first, SIGHT1000_LIST second) {
            double firstValue = Double.parseDouble(first.getData(7));
            double secondValue = Double.parseDouble(second.getData(7));

            if (firstValue > secondValue)
                return -1;
            else if (firstValue < secondValue)
                return 1;
            else
                return 0;
        }
    }

    static public void setRecommendCountById(String placeId, String recommendCount) {
        for(int i=0; i<sight1000Array.size(); i++) {
            if(sight1000Array.get(i).getData(0).equals(placeId)) {
                sight1000Array.get(i).setData(3, recommendCount);
            }
        }
    }
}