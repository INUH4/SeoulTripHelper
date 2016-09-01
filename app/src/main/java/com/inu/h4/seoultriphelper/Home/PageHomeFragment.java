package com.inu.h4.seoultriphelper.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inu.h4.seoultriphelper.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class PageHomeFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.page_home, container, false);

        Fragment weeklyRanking = new HomeWeeklyRankingFragment();
        Fragment monthlyRanking = new HomeMonthlyRankingFragment();

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(weeklyRanking);
        fragments.add(monthlyRanking);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            /*
            @Override
            public CharSequence getPageTitle(int position) {
                return fragments.get(position).getTitle(getActivity());
            }
            */
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        //getActivity().setTitle(fragments.get(0).getTitle(getActivity()));
        ViewPager pager = (ViewPager) layout.findViewById(R.id.home_viewpager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //getActivity().setTitle(fragments.get(position).getTitle(getActivity()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pager.setAdapter(adapter);
        ((SmartTabLayout) layout.findViewById(R.id.home_viewpagertab)).setViewPager(pager);

        getActivity().setTitle("홈 화면");
        return layout;
    }
}