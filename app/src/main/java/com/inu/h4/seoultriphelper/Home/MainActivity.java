package com.inu.h4.seoultriphelper.Home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.inu.h4.seoultriphelper.Bucket.PageBucketEmptyFragment;
import com.inu.h4.seoultriphelper.Bucket.PageBucketExistFragment;
import com.inu.h4.seoultriphelper.Planner.PagePlannerEmptyFragment;
import com.inu.h4.seoultriphelper.Planner.PagePlannerExistFragment;
import com.inu.h4.seoultriphelper.Prefer.PagePreferEmptyFragment;
import com.inu.h4.seoultriphelper.Prefer.PagePreferExistFragment;
import com.inu.h4.seoultriphelper.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment pageHomeFragment;
    Fragment pagePreferEmptyFragment;
    Fragment pagePreferExistFragment;
    Fragment pageBucketEmptyFragment;
    Fragment pageBucketExistFragment;
    Fragment pagePlannerEmptyFragment;
    Fragment pagePlannerExistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각 페이지에 해당하는 Fragment 초기화
        pageHomeFragment = new PageHomeFragment();
        pagePreferEmptyFragment = new PagePreferEmptyFragment();
        pagePreferExistFragment = new PagePreferExistFragment();
        pageBucketEmptyFragment = new PageBucketEmptyFragment();
        pageBucketExistFragment = new PageBucketExistFragment();
        pagePlannerEmptyFragment = new PagePlannerEmptyFragment();
        pagePlannerExistFragment = new PagePlannerExistFragment();

        // 초기 화면으로 사용할 fragment 설정
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, pageHomeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        // 앱 최상단에 메뉴, 검색버튼과 화면 이름을 출력하는 툴바를 생성
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 좌측 메뉴를 생성하는 drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_search:
                //
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            transaction.replace(R.id.container, pageHomeFragment);
        } else if (id == R.id.nav_tag) {
            //transaction.replace(R.id.container, ???);
        } else if (id == R.id.nav_prefer) {
            if(true) {          // 설문 결과가 없을 경우
                transaction.replace(R.id.container, pagePreferEmptyFragment);
            } else {            // 설문 결과가 있을 경우
                transaction.replace(R.id.container, pagePreferExistFragment);
            }
        } else if (id == R.id.nav_bucket) {
            if(true) {          // 장바구니가 비어있을 경우
                transaction.replace(R.id.container, pageBucketEmptyFragment);
            } else {            // 장바구니가 비어있지 않은 경우
                transaction.replace(R.id.container, pageBucketExistFragment);
            }
        } else if (id == R.id.nav_planner) {
            if(true) {          // 플래너가 비어있을 경우
                transaction.replace(R.id.container, pagePlannerEmptyFragment);
            } else {            // 플래너가 비어있지 않은 경우
                transaction.replace(R.id.container, pagePlannerExistFragment);
            }

        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_setting) {

        }

        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}