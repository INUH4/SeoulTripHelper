package com.inu.h4.seoultriphelper.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.DataBase.InsertDB_REVIEW1000;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1000ARRAY;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1000_LIST;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1100ForDetailImage;
import com.inu.h4.seoultriphelper.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeMonthlyRankingFragment extends Fragment {
    private ListView listView;
    private ArrayList<HomeRankingListViewItem> data;
    private static HomeRankingListViewAdapter adapter;

    private static final String SERVER_IP = "http://52.42.208.72/";

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LOG/MONTH", "onStart()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("LOG/MONTH", "onCreatedView()");
        View layout = inflater.inflate(R.layout.home_monthly_ranking, container, false);
        adapter = new HomeRankingListViewAdapter(this);

        listView = (ListView) layout.findViewById(R.id.home_monthly_ranking_list_view);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        getData();
        refresh();

        return layout;
    }

    public void getData(){
        data = new ArrayList<>();
        HomeRankingListViewItem item;
        SIGHT1000ARRAY.Monthsorting();
        for(int i = 0; i< SIGHT1000ARRAY.sight1000Array.size(); i++) {
            item = new HomeRankingListViewItem();
            //Log.d("LOG/MONTH", "beforeGetData");
            SIGHT1000ARRAY.getMonthArrayData(item, i);
            LoadBitmapfromUrl(SIGHT1000ARRAY.sight1000Array.get(i).getData(8), item);
            //Log.d("LOG/MONTH", "GetData : " + item.getSightName());
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
            }
        }
        LoadClass inner = new LoadClass();
        inner.execute(uri);
    }

    static public void refresh(){
        adapter.notifyDataSetChanged();
        Log.d("LOG/MONTH", "Refresh");
    }

}