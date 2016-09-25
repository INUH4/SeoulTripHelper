package com.inu.h4.seoultriphelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.inu.h4.seoultriphelper.Bucket.PageBucketEmptyFragment;
import com.inu.h4.seoultriphelper.Bucket.PageBucketExistFragment;
import com.inu.h4.seoultriphelper.Home.PageHomeFragment;
import com.inu.h4.seoultriphelper.Planner.PagePlannerEmptyFragment;
import com.inu.h4.seoultriphelper.Planner.PagePlannerExistFragment;
import com.inu.h4.seoultriphelper.Prefer.PagePreferEmptyFragment;
import com.inu.h4.seoultriphelper.Prefer.PagePreferExistFragment;
import com.inu.h4.seoultriphelper.Setting.SettingActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    Fragment initFragment;
    FragmentTransaction transaction;

    private BackPressCloseSystem backPressCloseSystem;

    @Override
    public void onBackPressed() {
        Log.d("LOG/MAIN", "onBackPressed()");

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

        // 뒤로가기 버튼 이벤트 등록
        backPressCloseSystem = new BackPressCloseSystem(this);

        // 각 페이지에 해당하는 Fragment 초기화
        initFragment = new PageHomeFragment();

        // 초기 화면으로 사용할 fragment 설정
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, initFragment, "page_home");
        transaction.addToBackStack("page_home");
        transaction.commit();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("LOG/MAIN", "onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
            targetFragment = new PageHomeFragment();
            tag = "page_home";
        } else if (id == R.id.nav_tag) {
            //targetFragment = new ??();
            //tag = "page_tag";
        } else if (id == R.id.nav_prefer) {
            if(true) {          // 설문 결과가 없을 경우
                targetFragment = new PagePreferEmptyFragment();
                tag = "page_prefer_empty";
            } else {            // 설문 결과가 있을 경우
                targetFragment = new PagePreferExistFragment();
                tag = "page_prefer_exist";
            }
        } else if (id == R.id.nav_bucket) {
            if(true) {          // 장바구니가 비어있을 경우
                targetFragment = new PageBucketEmptyFragment();
                tag = "page_bucket_empty";
            } else {            // 장바구니가 비어있지 않은 경우
                targetFragment = new PageBucketExistFragment();
                tag = "page_bucket_exist";
            }
        } else if (id == R.id.nav_planner) {
            if(true) {          // 플래너가 비어있을 경우
                targetFragment = new PagePlannerEmptyFragment();
                tag = "page_planner_empty";
            } else {            // 플래너가 비어있지 않은 경우
                targetFragment = new PagePlannerExistFragment();
                tag = "page_planner_exist";
            }
        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        currentFragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager()
                .getBackStackEntryCount() - 1).getName();

        Log.d("LOG/MAIN", currentFragmentTag + " -> " + tag);

        // tag를 통해 fragment를 구분하고 바뀔 페이지가 같은 것이면 아무 동작도 하지 않는다.
        if(tag == currentFragmentTag) {
            if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                this.drawer.closeDrawer(GravityCompat.START);
            }
            Log.d("LOG/MAIN", "not replace");
            return true;
        }

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