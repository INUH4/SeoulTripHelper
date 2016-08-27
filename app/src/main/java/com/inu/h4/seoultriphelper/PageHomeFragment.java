package com.inu.h4.seoultriphelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class PageHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Fragment weeklyRanking;
    Fragment monthlyRanking;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagePreferEmptyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageHomeFragment newInstance(String param1, String param2) {
        PageHomeFragment fragment = new PageHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.page_home, container, false);

        Fragment weeklyRanking = new HomeRankingFragment();
        Fragment monthlyRanking = new HomeRankingFragment();

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