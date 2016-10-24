package com.inu.h4.seoultriphelper.DataBase;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;

import com.inu.h4.seoultriphelper.Detail.SightDetailFragment;
import com.inu.h4.seoultriphelper.Detail.SightDetailItem;
import com.inu.h4.seoultriphelper.Detail.SightDetailReviewListViewAdapter;
import com.inu.h4.seoultriphelper.Detail.SightDetailReviewListViewItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectDB_SIGHT1100ForDetailImage {

    static String myJSON;

    private static final String SERVER_IP = "http://52.42.208.72/";
    private static final String TAG_RESULTS = "result";
    private static final String IMAGE_FILEPATH = "SIGHT_IMAGE_FILEPATH";
    private static final String IMAGE_FILENAME = "SIGHT_IMAGE_FILENAME";

    public static void getData(String sight_id, final List<String> imageUris) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params){
                try{
                    String sight_id = params[0];

                    String link = "http://52.42.208.72/Sight1100GetDataForDetail.php";
                    String data = URLEncoder.encode("SIGHT_ID", "UTF-8") + "=" + URLEncoder.encode(sight_id, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null){
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch (Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override
            protected void onPostExecute(String result){
                try{
                    JSONObject root = new JSONObject(result);
                    JSONArray ja = root.getJSONArray(TAG_RESULTS);

                    for(int i=0;i<ja.length();i++) {
                        JSONObject obj = ja.getJSONObject(i);
                        String sightImageFilePath = obj.getString(IMAGE_FILEPATH);
                        String sightImageFileName = obj.getString(IMAGE_FILENAME);

                        String fullPath = SERVER_IP.concat(sightImageFilePath).concat(sightImageFileName);

                        imageUris.add(fullPath);

                        Log.d("LOG/DB","Get Item");
                    }

                    SightDetailFragment.LoadBitmapfromUrl(imageUris);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(sight_id);
    }
}
