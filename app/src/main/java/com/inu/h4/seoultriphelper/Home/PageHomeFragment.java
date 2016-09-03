package com.inu.h4.seoultriphelper.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inu.h4.seoultriphelper.R;

public class PageHomeFragment extends Fragment {

    Fragment weeklyRanking;
    Fragment monthlyRanking;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("aaa", "Home - onStart()");

        weeklyRanking = new HomeWeeklyRankingFragment();
        monthlyRanking = new HomeMonthlyRankingFragment();

        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.home_viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(weeklyRanking, "주간 랭킹");
        adapter.addFragment(monthlyRanking, "월간 랭킹");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.home_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.page_home, container, false);

        getActivity().setTitle("홈 화면");
        return layout;
    }
}