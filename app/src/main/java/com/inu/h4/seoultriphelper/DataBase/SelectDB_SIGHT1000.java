package com.inu.h4.seoultriphelper.DataBase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.inu.h4.seoultriphelper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016-08-14.
 */
public class SelectDB_SIGHT1000 extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_SIGHT_ID = "sight_id";
    private static final String TAG_SIGHT_NAME = "sight_name";
    private static final String TAG_SIGHT_INFO = "sight_info";
    private static final String TAG_SIGHT_RECOMMEND_COUNT = "sight_recommend_count";
    private static final String TAG_SIGHT_LOCATION_X = "sight_location_x";
    private static final String TAG_SIGHT_LOCATION_Y = "sight_location_y";
    private static final String TAG_SIGHT_WEEKRECOMMEND = "sight_weekrecommend";
    private static final String TAG_SIGHT_MONTHRECOMMEND = "sight_monthrecommend";

    JSONArray sights = null;

    ArrayList<HashMap<String, String>> sightList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_list);
        list = (ListView) findViewById(R.id.trip_list_id);
        sightList = new ArrayList<>();
        getData("http://52.42.208.72/Sight1000GetData.php");
    }

    protected void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            sights = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0; i<sights.length(); i++){
                JSONObject c = sights.getJSONObject(i);
                String sight_id = c.getString(TAG_SIGHT_ID);
                String sight_name = c.getString(TAG_SIGHT_NAME);
                String sight_info = c.getString(TAG_SIGHT_INFO);
                String sight_recommend_count = c.getString(TAG_SIGHT_RECOMMEND_COUNT);
                String sight_location_x = c.getString(TAG_SIGHT_LOCATION_X);
                String sight_location_y = c.getString(TAG_SIGHT_LOCATION_Y);
                String sight_weekrecommend = c.getString(TAG_SIGHT_WEEKRECOMMEND);
                String sight_monthrecommend = c.getString(TAG_SIGHT_MONTHRECOMMEND);

                HashMap<String, String> persons = new HashMap<>();

                persons.put(TAG_SIGHT_ID, sight_id);
                persons.put(TAG_SIGHT_NAME, sight_name);
                persons.put(TAG_SIGHT_INFO, sight_info);
                persons.put(TAG_SIGHT_RECOMMEND_COUNT, sight_recommend_count);
                persons.put(TAG_SIGHT_LOCATION_X, sight_location_x);
                persons.put(TAG_SIGHT_LOCATION_Y, sight_location_y);
                persons.put(TAG_SIGHT_WEEKRECOMMEND, sight_weekrecommend);
                persons.put(TAG_SIGHT_MONTHRECOMMEND, sight_monthrecommend);

                sightList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SelectDB_SIGHT1000.this, sightList, R.layout.trip_list_member,
                    new String[]{TAG_SIGHT_ID, TAG_SIGHT_NAME, TAG_SIGHT_INFO, TAG_SIGHT_RECOMMEND_COUNT, TAG_SIGHT_LOCATION_X, TAG_SIGHT_LOCATION_Y, TAG_SIGHT_WEEKRECOMMEND, TAG_SIGHT_MONTHRECOMMEND},
                    new int[]{R.id.sight_id, R.id.sight_name, R.id.sight_info, R.id.sight_recommend_count, R.id.sight_location_x, R.id.sight_location_y, R.id.sight_weekrecommend, R.id.sight_monthrecommend}
            );

            list.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String urlname = params[0];
                BufferedReader bufferedReader;

                try {
                    URL url = new URL(urlname);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();
                }catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
