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
import android.widget.ListView;
import android.widget.TableRow;

import com.inu.h4.seoultriphelper.R;

import java.util.ArrayList;

public class PageTagFragment extends Fragment implements View.OnClickListener {

    // TODO : 나중에 지역 태그 변수 이름 바꿔주세요. 불필요하다면 안 해도 됌. (예시 : tag1 -> tag_palace)
    // 지역 태그 관련
    private Button tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8;
    private TableRow mTableRow;

    // 리스트 출력 관련 변수
    private ListView mListView;
    private ArrayList<TagContentListItem> mData;
    private TagContentListItemAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LOG/PAGE_TAG", "onCreateView()");

        View layout = inflater.inflate(R.layout.page_tag, container, false);

        adapter = new TagContentListItemAdapter();

        mListView = (ListView) layout.findViewById(R.id.tag_content_listview);
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadData();
        addListViewItem(mData);

        tag1 = (Button) layout.findViewById(R.id.btn_tag1);
        tag2 = (Button) layout.findViewById(R.id.btn_tag2);
        tag3 = (Button) layout.findViewById(R.id.btn_tag3);
        tag4 = (Button) layout.findViewById(R.id.btn_tag4);
        tag5 = (Button) layout.findViewById(R.id.btn_tag5);
        tag6 = (Button) layout.findViewById(R.id.btn_tag6);
        tag7 = (Button) layout.findViewById(R.id.btn_tag7);
        tag8 = (Button) layout.findViewById(R.id.btn_tag8);
        mTableRow = (TableRow) layout.findViewById(R.id.additional_menu);

        tag1.setOnClickListener(this);
        tag2.setOnClickListener(this);
        tag3.setOnClickListener(this);
        tag5.setOnClickListener(this);
        tag6.setOnClickListener(this);
        tag7.setOnClickListener(this);
        tag8.setOnClickListener(this);

        tag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tag4를 누르면 tag5, 6, 7, 8이 보이게 함.
                mTableRow.setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.tag_select_menu_container)
                        .setVisibility(View.VISIBLE);

                // 하위 태그 메뉴를 선택할 fragment를 띄움
                TagSelectMenuFragment fragment = new TagSelectMenuFragment();
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.tag_select_menu_container, fragment)
                        .commit();
            }
        });

        getActivity().setTitle("태그별 보기");
        return layout;
    }

    @Override
    public void onClick(View v) {
        // 클릭한 장소 태그 버튼(대분류)의 문자열을 받아온다
        String LocationTag = ((Button) v).getText().toString();

        // 선택한 버튼 태그의 문자열과 같은 장소를 출력 전용 리스트에 저장
        adapter.removeItem(); // 어댑터에 저장한 목록을 일단 비워주고 새로운 목록을 채워넣음.

        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getLocationTypeTag().equals(LocationTag)) {
                adapter.addItem(mData.get(i)); // 새 목록 채우기
            }
        }

        // 리스트뷰 갱신
        adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);
    }

    public void loadData() {
        mData = new ArrayList<>();

        TagContentListItem item = new TagContentListItem();
        item.setSightName("tag1, 1, 3, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag1");
        item.addAttribute("1");
        item.addAttribute("3");
        item.addAttribute("4");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag1, 3");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag1");
        item.addAttribute("3");
        mData.add(item);


        item = new TagContentListItem();
        item.setSightName("tag2, 1, 2");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag2");
        item.addAttribute("1");
        item.addAttribute("2");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag2, 1");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag2");
        item.addAttribute("1");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag3, 3, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag3");
        item.addAttribute("3");
        item.addAttribute("4");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag4, 1, 3");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag4");
        item.addAttribute("1");
        item.addAttribute("3");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag5, 2, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag5");
        item.addAttribute("2");
        item.addAttribute("4");
        mData.add(item);
    }

    public void addListViewItem(ArrayList<TagContentListItem> item) {
        for(int i = 0; i < item.size();i++) {
            adapter.addItem(item.get(i));
        }
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