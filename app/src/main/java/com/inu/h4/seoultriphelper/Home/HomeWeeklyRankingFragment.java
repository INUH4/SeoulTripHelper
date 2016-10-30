package com.inu.h4.seoultriphelper.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.DataBase.SIGHT1000ARRAY;
import com.inu.h4.seoultriphelper.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeWeeklyRankingFragment extends Fragment {
    private ListView listView;
    private ArrayList<HomeRankingListViewItem> data;
    private static HomeRankingListViewAdapter adapter;
    private static int synk;

    private static final String SERVER_IP = "http://52.42.208.72/";

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LOG/WEEK", "onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        synk = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("LOG/WEEK", "onCreateView()");
        
        View layout = inflater.inflate(R.layout.home_weekly_ranking, container, false);
        adapter = new HomeRankingListViewAdapter(this);

        listView = (ListView) layout.findViewById(R.id.home_weekly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        synk = 1;

        getData();
        refresh();

        return layout;
    }

    public void getData(){
        data = new ArrayList<>();
        HomeRankingListViewItem item;
        SIGHT1000ARRAY.Weeksorting();
        for(int i = 0; i< SIGHT1000ARRAY.sight1000Array.size(); i++) {
            item = new HomeRankingListViewItem();
            //Log.d("LOG/WEEK", "beforeGetData");
            SIGHT1000ARRAY.getWeekArrayData(item, i);
            LoadBitmapfromUrl(SIGHT1000ARRAY.sight1000Array.get(i).getData(8), item);
            //Log.d("LOG/WEEK", "GetData : " + item.getSightName());
            data.add(item);
            adapter.addItem(data.get(i));
        }
    }
    // Uri -> 비트맵으로의 전환 메서드.
    public static void LoadBitmapfromUrl(final String uri, final HomeRankingListViewItem item) {
        class LoadClass extends AsyncTask< Object, Void, Bitmap> {
            @Override
            protected Bitmap doInBackground( Object... params ) {
                String uri = (String) params[0];
                return loadBitmap( uri );
            }

            @Override
            protected void onPostExecute( Bitmap result ) {
                if(result != null) {
                    item.setImageBitmap(result);
                    refresh();
                }
            }

            public Bitmap loadBitmap( String uri ) {
                if(synk == 1) {
                    Bitmap bitmap = null;
                    URL newurl = null;
                    bitmap = null;
                    try {
                        newurl = new URL(SERVER_IP.concat(uri));
                        bitmap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    Log.d("LOG/MONTH", "Get Home Bitmap! " + uri);

                    return bitmap;
                } else
                    return null;
            }
        }
        LoadClass inner = new LoadClass();
        inner.execute(uri);
    }

    public static void refresh(){
        adapter.notifyDataSetChanged();
        Log.d("LOG/WEEK", "Refresh");
    }
}