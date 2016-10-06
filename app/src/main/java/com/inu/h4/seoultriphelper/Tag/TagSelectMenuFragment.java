package com.inu.h4.seoultriphelper.Tag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inu.h4.seoultriphelper.R;
import com.inu.h4.seoultriphelper.Tag.TagGroup.TagView;

import java.util.ArrayList;

/**
 * Created by MIN on 2016-10-03.
 */

public class TagSelectMenuFragment extends Fragment {

    private ArrayList<String> categories;
    private TagGroup mTagGroup;
    private Button confirm, cancle;

    final private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().findViewById(R.id.tag_select_menu_container).setVisibility(View.GONE);
            getActivity().findViewById(R.id.additional_menu).setVisibility(View.GONE);
            onDestroyView();

            if(v.getId() == R.id.btn_tag_confirm)
            {
                // todo : 선택 태그에 관한 정보를 넘기는 코드 작성
            }
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

        // 확인, 취소버튼
        confirm = (Button) layout.findViewById(R.id.btn_tag_confirm);
        cancle = (Button) layout.findViewById(R.id.btn_tag_cancle);

        // 임시 데이터 설정
        categories = new ArrayList<>();
        categories.add("subtag1");
        categories.add("subtag2");
        categories.add("subtag3");
        categories.add("subtag4");
        categories.add("subtag5");

        // 임시 데이터를 태그 그룹에 삽입하여 띄움
        mTagGroup = (TagGroup) layout.findViewById(R.id.tag_group);
        mTagGroup.setTags(categories); // 태그 초기화 및 띄우기
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                // 선택한 태그가 무엇인지 알아낸 후 태그의 색을 변환시킴
                int index = categories.indexOf(tag);
                TagView tagView = mTagGroup.getTagAt(index);
                tagView.setBackGroundColor(Color.LTGRAY);
                tagView.setTextColor(Color.RED);
            }
        });

        confirm.setOnClickListener(mOnClick);
        cancle.setOnClickListener(mOnClick);

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
