package com.inu.h4.seoultriphelper.Tag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.inu.h4.seoultriphelper.DataBase.SIGHT1000ARRAY;
import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

public class TagMainFragment extends Fragment {
    // 태그 관련 변수들
    //private Button tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9;
    private Button[] tags;
    private LinearLayout hiddenTagRow;

    // 리스트 출력 관련 변수
    private ListView mListView;
    private ArrayList<TagContentItem> mData;
    private TagContentAdapter adapter;
//    private SelectedTagInstance instance;

    final private View.OnClickListener hiddenMenuInflater = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // tag4를 누르면 tag5, 6, 7, 8이 보이며, tag4의 클릭 리스너가 변경됨.
            hiddenTagRow.setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.tag_select_menu_container)
                    .setVisibility(View.VISIBLE);

            // 하위 태그 메뉴를 선택할 fragment를 띄움
            TagSelectMenuFragment fragment = new TagSelectMenuFragment();
            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.tag_select_menu_container, fragment)
                    .commit();

            getActivity().findViewById(R.id.tag_select_menu_container).bringToFront();
            mListView.setAlpha(0.6f);

            tags[3].setOnClickListener(onTagButtonClick);
            tags[3].setText("도심");
        }
    };

    final private View.OnClickListener onTagButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 클릭한 장소 태그 버튼(대분류)의 문자열을 받아온다
            String LocationTag = ((Button) v).getText().toString();

            if(LocationTag.equals(tags[0].getText().toString())) {
                // tag1을 누르면 전체 내용 출력(모든 장소 태그)
                adapter.removeItem();

                adapter.notifyDataSetChanged();
                mListView.setAdapter(adapter);
            } else {
                // 선택한 버튼 태그의 문자열과 같은 장소를 출력 전용 리스트에 저장
                adapter.removeItem(); // 어댑터에 저장한 목록을 일단 비워주고 새로운 목록을 채워넣음.

//                for (int i = 0; i < mData.size(); i++) {
//                    if (mData.get(i).getLocationTypeTag().equals(LocationTag)) {
//                        adapter.addItem(mData.get(i)); // 새 목록 채우기
//                    }
//                }

//                SelectedTagInstance.setMaintag(LocationTag);
            }

            // 리스트뷰 갱신
            adapter.notifyDataSetChanged();
            mListView.setAdapter(adapter);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onCreateView()");
        View layout = inflater.inflate(R.layout.tag_main, container, false);

//        instance.removeInstance(); // 인스턴스를 비운다
//        instance.getInstance(); //  인스턴스를 새로 생성
        adapter = new TagContentAdapter();

        initView(layout);

        getData();
        refresh();

        getActivity().setTitle("태그별 보기");
        return layout;
    }

    private void initView(View layout) {
        mListView = (ListView) layout.findViewById(R.id.tag_content_listview);
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        tags = new Button[9];

        tags[0] = (Button) layout.findViewById(R.id.btn_tag1);
        tags[1] = (Button) layout.findViewById(R.id.btn_tag2);
        tags[2] = (Button) layout.findViewById(R.id.btn_tag3);
        tags[3] = (Button) layout.findViewById(R.id.btn_tag4);
        tags[4] = (Button) layout.findViewById(R.id.btn_tag5);
        tags[5] = (Button) layout.findViewById(R.id.btn_tag6);
        tags[6] = (Button) layout.findViewById(R.id.btn_tag7);
        tags[7] = (Button) layout.findViewById(R.id.btn_tag8);
        tags[8] = (Button) layout.findViewById(R.id.btn_tag9);
        hiddenTagRow = (LinearLayout) layout.findViewById(R.id.hidden_tag_row);

        for(int i = 0; i < tags.length ; i++) {
            tags[i].setOnClickListener(onTagButtonClick);
        }

        tags[3].setOnClickListener(hiddenMenuInflater);

        tags[0].setText("전체");
        tags[1].setText("유적");
        tags[2].setText("명소");
        tags[3].setText("더보기");
        tags[4].setText("시장");
        tags[5].setText("산");
        tags[6].setText("유원지");
        tags[7].setText("휴양림");
        tags[8].setText("박물관");
    }


    public void getData(){
        mData = new ArrayList<>();
        TagContentItem item;
        for(int i = 0; i < SIGHT1000ARRAY.sight1000Array.size(); i++) {
            item = new TagContentItem();
            SIGHT1000ARRAY.getDataForTag(item, i);
            Log.d("LOG/TAG", "GetData : " + item.getSightName());
            mData.add(item);
            adapter.addItem(mData.get(i));
        }
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
        Log.d("LOG/MONTH", "Refresh");
    }

    public void setOnChildButtonClick() {
//        instance = SelectedTagInstance.getInstance(); // singleton 객체의 인스턴스를 받아옴.

//        if(!instance.getSubtag().isEmpty()) { // 서브태그 목록이 비어 있지 않은 경우
//            adapter.removeItem(); // 어댑터의 내용을 비움
//            Log.d("LOG/PAGE_TAG", "adapter cleared.");
//            for(TagContentItem item : mData) {
//                ArrayList<String> attribute = item.getAttribute();
//
//                for(int i = 0; i < instance.getSubtag().size(); i++) { // 저장된 서브태그의 갯수만큼
//                    if(attribute.contains(instance.getSubtag().get(i))) { // 서브태그가 포함되었는지 검사
//                        adapter.addItem(item);
//                        break;
//                    }
//                }
//            }
//        }

        adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);

        tags[3].setOnClickListener(hiddenMenuInflater);
        tags[3].setText("더보기");
    }

    @Override
    public void onStart() {
        Log.d("LOG/PAGE_TAG", "onStart()");
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d("LOG/PAGE_TAG", "onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LOG/PAGE_TAG", "onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LOG/PAGE_TAG", "onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("LOG/PAGE_TAG", "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("LOG/PAGE_TAG", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("LOG/PAGE_TAG", "onDetach()");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("LOG/PAGE_TAG", "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }
}