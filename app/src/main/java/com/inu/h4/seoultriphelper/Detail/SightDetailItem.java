package com.inu.h4.seoultriphelper.Detail;

import java.util.List;

public class SightDetailItem {
    String sightName;
    String sightInfo;
    List<String> imageUrls;
    int recommendCount;
    double sightX, sightY;

    public String getSightName() {
        return sightName;
    }
    public void setSightName(String sightName) {
        this.sightName = sightName;
    }
    public String getSightInfo() {
        return sightInfo;
    }
    public void setSightInfo(String sightInfo) {
        this.sightInfo = sightInfo;
    }
    public List<String> getImageUrls() {
        return imageUrls;
    }
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
    public int getRecommendCount() {
        return recommendCount;
    }
    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }
    public double getSightX() {
        return sightX;
    }
    public void setSightX(double sightX) {
        this.sightX = sightX;
    }
    public double getSightY() {
        return sightY;
    }
    public void setSightY(double sightY) {
        this.sightY = sightY;
    }
}
