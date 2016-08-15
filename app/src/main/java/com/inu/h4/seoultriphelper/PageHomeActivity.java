package com.inu.h4.seoultriphelper;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PageHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button tempDetailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        tempDetailButton = (Button) findViewById(R.id.btn_temp_detail);
        tempDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageHomeActivity.this, PageSightDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_tag) {

        } else if (id == R.id.nav_prefer) {
            if(true) {          // 설문 결과가 없을 경우
                intent = new Intent(PageHomeActivity.this, PagePreferEmptyActivity.class);
            } else {            // 설문 결과가 있을 경우
                intent = new Intent(PageHomeActivity.this, PagePreferExistActivity.class);
            }
        } else if (id == R.id.nav_bucket) {
            if(true) {          // 장바구니가 비어있을 경우
                intent = new Intent(PageHomeActivity.this, PageBucketEmptyActivity.class);
            } else {            // 장바구니가 비어있지 않은 경우
                intent = new Intent(PageHomeActivity.this, PageBucketExistActivity.class);
            }
        } else if (id == R.id.nav_planner) {
            if(true) {          // 플래너가 비어있을 경우
                intent = new Intent(PageHomeActivity.this, PagePlannerEmptyActivity.class);
            } else {            // 플래너가 비어있지 않은 경우
                intent = new Intent(PageHomeActivity.this, PagePlannerExistActivity.class);
            }

        } else if (id == R.id.nav_map) {

        } else if (id == R.id.nav_setting) {

        }
        if(intent != null) {
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}