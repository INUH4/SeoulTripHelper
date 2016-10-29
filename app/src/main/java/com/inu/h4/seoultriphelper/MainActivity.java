package com.inu.h4.seoultriphelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.inu.h4.seoultriphelper.Bucket.BucketEmptyFragment;
import com.inu.h4.seoultriphelper.Bucket.BucketExistFragment;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1000ARRAY;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1000_LIST;
import com.inu.h4.seoultriphelper.DataBase.SIGHT1100ForDetailImage;
import com.inu.h4.seoultriphelper.Home.HomeFragment;
import com.inu.h4.seoultriphelper.Planner.PlannerEmptyFragment;
import com.inu.h4.seoultriphelper.Planner.PlannerExistFragment;
import com.inu.h4.seoultriphelper.Prefer.PreferEmptyFragment;
import com.inu.h4.seoultriphelper.Prefer.PreferExistFragment;
import com.inu.h4.seoultriphelper.Setting.SettingActivity;
import com.inu.h4.seoultriphelper.Tag.TagMainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SIGHT1000_LIST sight1000list;
    phpDown task1;
    static Bitmap bmImg;

    DrawerLayout drawer;
    Fragment initFragment;
    FragmentTransaction transaction;

    private BackPressCloseSystem backPressCloseSystem;

    @Override
    public void onBackPressed() {
        Log.d("LOG/MAIN", "onBackPressed()");

        // DB 데이터를 불러오는 중에 뒤로가기를 눌렀을 경우.
        if(SIGHT1100ForDetailImage.synk != SIGHT1100ForDetailImage.STOP) {
            Log.d("LOG/MAIN", "SIGHT1100 synk = STOP");
            SIGHT1100ForDetailImage.synk = SIGHT1100ForDetailImage.STOP;
        }

        // 좌측의 drawer 메뉴가 켜져있는 경우 뒤로가기 버튼을 누르면 닫음
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressCloseSystem.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LOG/MAIN", "onCreate()");

        task1 = new phpDown();
        task1.execute("http://52.42.208.72/Sight1000GetData.php");

        // 뒤로가기 버튼 이벤트 등록
        backPressCloseSystem = new BackPressCloseSystem(this);

        // 앱 최상단에 메뉴, 검색버튼과 화면 이름을 출력하는 툴바를 생성
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 좌측 메뉴를 생성하는 drawer layout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private class phpDown extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str){
            try{
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("result");
                for(int i=0; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String id = jo.getString("sight_id");
                    String name = jo.getString("sight_name");
                    String info = jo.getString("sight_info");
                    String recommend_count = jo.getString("sight_recommend_count");
                    String location_x = jo.getString("sight_location_x");
                    String location_y = jo.getString("sight_location_y");
                    String weekrecommend = jo.getString("sight_weekrecommend");
                    String monthrecommend = jo.getString("sight_monthrecommend");
                    String thumbnail = jo.getString("sight_thumbnail");

                    sight1000list = new SIGHT1000_LIST();
                    sight1000list.setSight1000Data(
                            id,
                            name,
                            info,
                            recommend_count,
                            location_x,
                            location_y,
                            weekrecommend,
                            monthrecommend,
                            thumbnail);

                    SIGHT1000ARRAY.sight1000Array.add(sight1000list);
                    Log.d("LOG/HOME", name);
                }
                // 각 페이지에 해당하는 Fragment 초기화
                initFragment = new HomeFragment();
                // 초기 화면으로 사용할 fragment 설정
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.container, initFragment, "page_home");
                transaction.addToBackStack("page_home");
                transaction.commit();
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("LOG/MAIN", "onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.main, menu);

        // SearchView Hint 변경하기
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("여행지를 검색해 보세요.");

        // SearchView 입력 글자색과 힌트 색상 변경하기
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView
                .findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.GRAY);
        searchAutoComplete.setTextColor(Color.WHITE);

        // SearchView 확장/축소 이벤트 처리
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "SearchView 확장", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "SearchView 축소", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);

        // SearchView 검색어 입력/검색 이벤트 처리
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "검색한 명령어 : " + query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "입력중인 단어 : " + newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("LOG/MAIN", "onOptionSelected()");
        switch(item.getItemId()) {
            case R.id.action_search:
                // TODO 돋보기 버튼 동작 삽입
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d("LOG/MAIN", "onNavigationItemSelected()");

        int id = item.getItemId();

        String tag = "";
        String currentFragmentTag = "";

        Fragment targetFragment = null;
        transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            targetFragment = new HomeFragment();
            tag = "page_home";
        } else if (id == R.id.nav_tag) {
            targetFragment = new TagMainFragment();
            tag = "page_tag";
        } else if (id == R.id.nav_prefer) {
            if(DataRepository.preferIndex == null) {          // 설문 결과가 없을 경우
                targetFragment = new PreferEmptyFragment();
                tag = "page_prefer_empty";
            } else {            // 설문 결과가 있을 경우
                targetFragment = new PreferExistFragment();
                tag = "page_prefer_exist";
            }
        } else if (id == R.id.nav_bucket) {
            if(true) {          // 장바구니가 비어있을 경우
                targetFragment = new BucketEmptyFragment();
                tag = "page_bucket_empty";
            } else {            // 장바구니가 비어있지 않은 경우
                targetFragment = new BucketExistFragment();
                tag = "page_bucket_exist";
            }
        } else if (id == R.id.nav_planner) {
            if(true) {          // 플래너가 비어있을 경우
                targetFragment = new PlannerEmptyFragment();
                tag = "page_planner_empty";
            } else {            // 플래너가 비어있지 않은 경우
                targetFragment = new PlannerExistFragment();
                tag = "page_planner_exist";
            }
        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        // 현재 띄워진 Fragment의 tag를 받아옴.
        currentFragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager()
                .getBackStackEntryCount() - 1).getName();

        // tag를 통해 Fragment를 구분하고 바뀔 페이지가 같은 것이면 아무 동작도 하지 않는다.
        if(tag.equals(currentFragmentTag)) {
            if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                this.drawer.closeDrawer(GravityCompat.START);
            }
            Log.d("LOG/MAIN", "not replace");
            return true;
        }

        Log.d("LOG/MAIN", currentFragmentTag + " -> " + tag);

        if(targetFragment == null) {
            if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                this.drawer.closeDrawer(GravityCompat.START);
            }
            return true;
        }

        transaction.replace(R.id.container, targetFragment, tag);
        transaction.addToBackStack(tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.commitAllowingStateLoss();
        //transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}