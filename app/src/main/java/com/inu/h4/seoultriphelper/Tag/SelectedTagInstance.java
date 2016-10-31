package com.inu.h4.seoultriphelper.Tag;

import java.util.ArrayList;

/**
 * Created by MIN on 2016-10-09.
 */
public class SelectedTagInstance {
    private static SelectedTagInstance instance = null;

    private String maintag;
    private ArrayList<String> subtag;

    private SelectedTagInstance() {
        maintag = "";
        subtag = new ArrayList<>();
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

    public String getMaintag() {
        return instance.maintag;
    }

    public static void setMaintag(String maintag) {
        instance.maintag = maintag;
    }

    public ArrayList<String> getSubtag() {
        return instance.subtag;
    }

    public void setSubtag(ArrayList<String> subtag) {
        instance.subtag = subtag;
    }
}