package com.inu.h4.seoultriphelper.Tag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.DataBase.TAG1000ARRAY;
import com.inu.h4.seoultriphelper.DataBase.TAG1100ARRAY;
import com.inu.h4.seoultriphelper.DataBase.TAG1200ARRAY;
import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

/**
 * Created by MIN on 2016-10-03.
 */

public class TagSelectMenuFragment extends Fragment {
    // 태그 그룹 관련
    private ListView mListView;
    private TagGroupListViewAdapter adapter;
//    private TagGroup[] tagGroups;
    private ArrayList<String> subtags;

    // 버튼 객체
    private Button btnConfirm, btnCancle;

    // 데이터 저장용
//    private SelectedTagInstance instance;
    private TAG1000ARRAY tag1000array;
    private TAG1100ARRAY tag1100array;
    private TAG1200ARRAY tag1200array;

    final private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().findViewById(R.id.tag_select_menu_container).setVisibility(View.GONE);
            getActivity().findViewById(R.id.hidden_tag_row).setVisibility(View.GONE);

//            if(v.getId() == R.id.btn_tag_confirm) { // 체크된 목록을 저장함.
//                instance.setSubtag(mTagGroup.getCheckedTagList());
//            }

//            Fragment fragment = getParentFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("placeId", SearchListViewItem.getPlaceId());
//            sightDetailFragment.setArguments(bundle);

            ((TagMainFragment)getParentFragment()).setOnChildButtonClick();
            getActivity().findViewById(R.id.tag_content_listview).setOnTouchListener(clickable);
            getActivity().findViewById(R.id.tag_content_listview).setAlpha(1.0f);

            onDestroyView();
        }
    };

    final private View.OnTouchListener clickable = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    // 온터치 리스너가 true를 반환하면 뷰의 동작을 제한할 수 있음.
    final private View.OnTouchListener nonClickable = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("LOG/TAG_SELECT", "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LOG/TAG_SELECT", "onCreateView()");
        View layout = inflater.inflate(R.layout.tag_select_menu, container, false);
//        instance.getInstance(); // 현재 선택한 태그 싱글턴 객체 불러오기
        tag1000array = TAG1000ARRAY.getInstance();
        tag1100array = TAG1100ARRAY.getInstance();
        tag1200array = TAG1200ARRAY.getInstance();

        int tagGroupSize = tag1000array.getData().size(); // 태그 그룹의 갯수
        int tagSize = tag1100array.getData().size(); // 태그 전체의 갯수
//        tagGroups = new TagGroup[tagGroupSize];

        // 리스트 뷰를 만져도 동작하지 않게끔 함.
        getActivity().findViewById(R.id.tag_content_listview).setOnTouchListener(nonClickable);

        // 확인, 취소버튼 설정
        btnConfirm = (Button) layout.findViewById(R.id.btn_tag_confirm);
        btnCancle = (Button) layout.findViewById(R.id.btn_tag_cancle);

        btnConfirm.setOnClickListener(mOnClick);
        btnCancle.setOnClickListener(mOnClick);

        mListView = (ListView) layout.findViewById(R.id.tag_group_listview);
        adapter = new TagGroupListViewAdapter();

        for(int i = 0; i < tagGroupSize; i++) {
            TagGroupListViewItem item = new TagGroupListViewItem(getActivity());

            subtags = new ArrayList<>();
            for(int j = 0; j < tagSize; j++) { // 현재 태그에 속한 서브태그 분류작업
                if(Integer.parseInt(tag1100array.getData().get(j).getData(2)) == (i + 1)) {
                    subtags.add(tag1100array.getData().get(j).getData(1));
                }
            }

            item.setAttribute(subtags);
            item.setGroupName(tag1000array.getData().get(i).getData(1));
            adapter.addItem(item);
        }

        mListView.setAdapter(adapter);

        return layout;
    }

    @Override
    public void onStart() {
        Log.d("LOG/TAG_SELECT", "onStart()");
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("LOG/TAG_SELECT", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("LOG/TAG_SELECT", "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d("LOG/TAG_SELECT", "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LOG/TAG_SELECT", "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LOG/TAG_SELECT", "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("LOG/TAG_SELECT", "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("LOG/TAG_SELECT", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("LOG/TAG_SELECT", "onDetach()");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("LOG/TAG_SELECT", "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }
}
