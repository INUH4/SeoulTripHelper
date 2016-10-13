package com.inu.h4.seoultriphelper.Home;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.net.Uri;

public class HomeRankingListViewItem {
    private int image;
    private int placeid;
    private int week_recommend;
    private int month_recommend;
    private int ranking;
    private String sightName;


    public int getPlaceid(){ return placeid;}
    public void setPlaceid(int placeid){this.placeid = placeid;}
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getWeek_recommend(){ return week_recommend; }
    public void setWeek_recommend(int week_recommend){ this.week_recommend = week_recommend; }
    public int getMonth_recommend(){ return month_recommend; }
    public void setMonth_recommend(int month_recommend){ this.month_recommend = month_recommend; }
    public String getSightName() {
        return sightName;
    }
    public void setSightName(String sightName) {
        this.sightName = sightName;
    }
}