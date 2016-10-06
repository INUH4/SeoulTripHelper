package com.inu.h4.seoultriphelper.Home;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.net.Uri;

public class HomeRankingListViewItem {
    private int image;
    private int placeid;
    private int ranking;
    private String sightName;
    private Button getBucketButton;
    private Button recommendButton;
    private Button moreInfoButton;



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
    public String getSightName() {
        return sightName;
    }
    public void setSightName(String sightName) {
        this.sightName = sightName;
    }
    public Button getGetBucketButton() {
        return getBucketButton;
    }
    public void setGetBucketButton(Button getBucketButton) {
        this.getBucketButton = getBucketButton;
    }
    public Button getRecommendButton() {
        return recommendButton;
    }
    public void setRecommendButton(Button recommendButton) {
        this.recommendButton = recommendButton;
    }
    public Button getMoreInfoButton() {
        return moreInfoButton;
    }
    public void setMoreInfoButton(Button moreInfoButton) {
        this.moreInfoButton = moreInfoButton;
    }
}