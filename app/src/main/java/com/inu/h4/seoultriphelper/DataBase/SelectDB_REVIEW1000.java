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
public class SelectDB_REVIEW1000 extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_REVIEW_ID = "review_id";
    private static final String TAG_REVIEW_WRITER = "review_writer";
    private static final String TAG_REVIEW_INFO = "review_info";
    private static final String TAG_REVIEW_DATE = "review_date";
    private static final String TAG_REVIEW_POINT = "review_point";
    private static final String TAG_SIGHT_ID = "sight_id";

    JSONArray sights = null;

    ArrayList<HashMap<String, String>> sightList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_list);
        list = (ListView) findViewById(R.id.review_list_id);
        sightList = new ArrayList<>();
        getData("http://52.42.208.72/Review1000GetData.php");
    }

    protected void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            sights = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0; i<sights.length(); i++){
                JSONObject c = sights.getJSONObject(i);
                String review_id = c.getString(TAG_REVIEW_ID);
                String review_writer = c.getString(TAG_REVIEW_WRITER);
                String review_info = c.getString(TAG_REVIEW_INFO);
                String review_date = c.getString(TAG_REVIEW_DATE);
                String review_point = c.getString(TAG_REVIEW_POINT);
                String sight_id = c.getString(TAG_SIGHT_ID);

                HashMap<String, String> persons = new HashMap<>();

                persons.put(TAG_REVIEW_ID, review_id);
                persons.put(TAG_REVIEW_WRITER, review_writer);
                persons.put(TAG_REVIEW_INFO, review_info);
                persons.put(TAG_REVIEW_DATE, review_date);
                persons.put(TAG_REVIEW_POINT, review_point);
                persons.put(TAG_SIGHT_ID, sight_id);

                sightList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SelectDB_REVIEW1000.this, sightList, R.layout.review_list_member,
                    new String[]{TAG_REVIEW_ID, TAG_REVIEW_WRITER, TAG_REVIEW_INFO, TAG_REVIEW_DATE, TAG_REVIEW_POINT, TAG_SIGHT_ID},
                    new int[]{R.id.review_id, R.id.review_writer, R.id.review_info, R.id.review_date, R.id.review_point, R.id.sight_id}
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
