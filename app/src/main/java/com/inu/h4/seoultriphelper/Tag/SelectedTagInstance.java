package com.inu.h4.seoultriphelper.Tag;

import com.inu.h4.seoultriphelper.DataBase.TAG1100ARRAY;

import java.util.ArrayList;

/**
 * Created by MIN on 2016-10-09.
 */
public class SelectedTagInstance {
    private static SelectedTagInstance instance = null;

    private String category;
    private ArrayList<String> subtags;
    private ArrayList<Integer> subtagID;

    private SelectedTagInstance() {
        category = "";
        subtags = new ArrayList<>();
    }

    public static SelectedTagInstance getInstance() {
        if(instance == null) {
            instance = new SelectedTagInstance();
        }

        return instance;
    }
    
    public static void removeInstance() {
        instance = null;
    }

    public static String getCategory() { return instance.category; }

    public static void setCategory(String category) { instance.category = category; }

    public static ArrayList<String> getSubtags() {
        return instance.subtags;
    }

    public static void setSubtags(ArrayList<String> subtags) {
        instance.subtags = subtags;
    }
    public static void addSubtags(String subtag) { instance.subtags.add(subtag); }
    public static void clearSubtags() { instance.subtags = new ArrayList<>(); }

    public static ArrayList<Integer> getSubtagId() { return instance.subtagID; }

    public static void matchSubtagId() {
        TAG1100ARRAY tag1100array = TAG1100ARRAY.getInstance();
        instance.subtagID = new ArrayList<>();

        for(int i = 0; i < tag1100array.getData().size(); i++) {
            if (instance.subtags.contains(tag1100array.getData().get(i).getData(1))) {
                instance.subtagID.add(Integer.parseInt(tag1100array.getData().get(i).getData(0)));
            }
        }
    }
}