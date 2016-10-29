package com.inu.h4.seoultriphelper.DataBase;

import android.graphics.Bitmap;
import android.util.Log;
/**
 * Created by Administrator on 2016-09-28.
 */
public class SIGHT1000_LIST {
    private String[] sight1000Data = new String[10];
    // [0] = 아이디
    // [1] = 이름
    // [2] = 상세정보
    // [3] = 총 추천 수
    // [4] = x좌표
    // [5] = y좌표
    // [6] = 주 추천 수
    // [7] = 월 추천 수
    // [8] = 경로
    // [9] = 평점

    public void setSight1000Data(String sight1000_id,
                                 String sight1000_name,
                                 String sight1000_info,
                                 String sight1000_recommend_count,
                                 String sight1000_location_x,
                                 String sight1000_location_y,
                                 String sight1000_weekrecommend,
                                 String sight1000_monthrecommend,
                                 String sight1000_thumbnail
                                 ){
        sight1000Data[0] = sight1000_id;
        sight1000Data[1] = sight1000_name;
        sight1000Data[2] = sight1000_info;
        sight1000Data[3] = sight1000_recommend_count;
        sight1000Data[4] = sight1000_location_x;
        sight1000Data[5] = sight1000_location_y;
        sight1000Data[6] = sight1000_weekrecommend;
        sight1000Data[7] = sight1000_monthrecommend;
        sight1000Data[8] = sight1000_thumbnail;
    }

    public String getData(int index){
        //Log.d("LOG/SIGHT1000_LIST", "getlistdata");
        return sight1000Data[index];
    }
    public void setData(int index, String value) {
        sight1000Data[index] = value;
    }
}