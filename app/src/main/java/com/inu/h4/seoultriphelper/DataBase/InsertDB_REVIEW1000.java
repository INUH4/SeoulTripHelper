package com.inu.h4.seoultriphelper.DataBase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inu.h4.seoultriphelper.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016-08-30.
 */
public class InsertDB_REVIEW1000 extends AppCompatActivity {

    private EditText editText_review_writer;
    private EditText editText_review_info;
    private EditText editText_review_point;
    private EditText editText_sight_id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_test);

        editText_review_writer = (EditText) findViewById(R.id.review_writer);
        editText_review_info = (EditText) findViewById(R.id.review_info);
        editText_review_point = (EditText) findViewById(R.id.review_point);
        editText_sight_id = (EditText) findViewById(R.id.sight_id);
    }

    public void insert(View view){
        String review_writer = editText_review_writer.getText().toString();
        String review_info = editText_review_info.getText().toString();
        String review_point = editText_review_point.getText().toString();
        String sight_id = editText_sight_id.getText().toString();

        InsertToDatabase(review_writer, review_info, review_point, sight_id);
    }

    private void InsertToDatabase(String review_writers, String review_infos, String review_points, String sight_ids){
        class InsertData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(InsertDB_REVIEW1000.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params){
                try{
                    String review_writer = params[0];
                    String review_info = params[1];
                    String review_point = params[2];
                    String sight_id = params[3];

                    String link = "http://52.42.208.72/Review1000SetData.php";
                    String data = URLEncoder.encode("REVIEW_WRITER", "UTF-8") + "=" + URLEncoder.encode(review_writer, "UTF-8");
                    data += "&" + URLEncoder.encode("REVIEW_INFO", "UTF-8") + "=" + URLEncoder.encode(review_info, "UTF-8");
                    data += "&" + URLEncoder.encode("REVIEW_POINT", "UTF-8") + "=" + URLEncoder.encode(review_point, "UTF-8");
                    data += "&" + URLEncoder.encode("SIGHT_ID", "UTF-8") + "=" +URLEncoder.encode(sight_id, "UTF-8");

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
        }

        InsertData task = new InsertData();
        task.execute(review_writers, review_infos, review_points, sight_ids);
    }
}