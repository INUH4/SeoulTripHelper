package com.inu.h4.seoultriphelper.Bucket;

import android.widget.Button;

/**
 * Created by yuk on 2016-09-27.
 */
public class BucketGridViewItem {
    private int id;
    private int image;
    private int recommend;
    private String sightName;
    private double coordinate_x;
    private double coordinate_y;

    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }

    public int getRecommend() {
        return recommend;
    }
    public void setRecommend(int recommend) { this.recommend = recommend; }

    public String getSightName() {
        return sightName;
    }
    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public double getCoordinate_x(){return coordinate_x;}
    public void setCoordinate_x(double coordinate_x){this.coordinate_x = coordinate_x;}

    public double getCoordinate_y(){return coordinate_y;}
    public void setCoordinate_y(double coordinate_y){this.coordinate_y=coordinate_y;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}