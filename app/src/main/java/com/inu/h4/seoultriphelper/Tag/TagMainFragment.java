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

public class TagMainFragment extends Fragment {

    // TODO : 나중에 지역 태그 변수 이름 바꿔주세요. 불필요하다면 안 해도 됌. (예시 : tag1 -> tag_palace)

    // 태그 관련 변수들
    private Button tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag4_hidden;
    private TableRow mTableRow;

    // 리스트 출력 관련 변수
    private ListView mListView;
    private ArrayList<TagContentListItem> mData;
    private TagContentListAdapter adapter;
    private SelectedTagInstance instance;

    final private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 클릭한 장소 태그 버튼(대분류)의 문자열을 받아온다
            String LocationTag = ((Button) v).getText().toString();

            if(LocationTag == "tag1") {
                // tag1을 누르면 전체 내용 출력
                addListViewItem(mData);
            } else {
                // 선택한 버튼 태그의 문자열과 같은 장소를 출력 전용 리스트에 저장
                adapter.removeItem(); // 어댑터에 저장한 목록을 일단 비워주고 새로운 목록을 채워넣음.

                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).getLocationTypeTag().equals(LocationTag)) {
                        adapter.addItem(mData.get(i)); // 새 목록 채우기
                        instance.setMaintag(LocationTag);
                    }
                }
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

        SelectedTagInstance.removeInstance(); // 서브태그 인스턴스를 비운다
        adapter = new TagContentListAdapter();

        loadData();
        addListViewItem(mData);

        mListView = (ListView) layout.findViewById(R.id.tag_content_listview);
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        tag1 = (Button) layout.findViewById(R.id.btn_tag1);
        tag2 = (Button) layout.findViewById(R.id.btn_tag2);
        tag3 = (Button) layout.findViewById(R.id.btn_tag3);
        tag4 = (Button) layout.findViewById(R.id.btn_tag4);
        tag5 = (Button) layout.findViewById(R.id.btn_tag5);
        tag6 = (Button) layout.findViewById(R.id.btn_tag6);
        tag7 = (Button) layout.findViewById(R.id.btn_tag7);
        tag8 = (Button) layout.findViewById(R.id.btn_tag8);
        tag4_hidden = (Button) layout.findViewById(R.id.btn_tag4_hidden);
        mTableRow = (TableRow) layout.findViewById(R.id.additional_menu);

        tag1.setOnClickListener(mOnClick);
        tag2.setOnClickListener(mOnClick);
        tag3.setOnClickListener(mOnClick);

        tag5.setOnClickListener(mOnClick);
        tag6.setOnClickListener(mOnClick);
        tag7.setOnClickListener(mOnClick);
        tag8.setOnClickListener(mOnClick);

        tag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tag4를 누르면 tag5, 6, 7, 8이 보이게 되고
                // tag4 버튼이 tag4_hidden으로 교체됨.
                tag4.setVisibility(View.GONE);
                tag4_hidden.setVisibility(View.VISIBLE);
                tag4_hidden.setOnClickListener(mOnClick);

                mTableRow.setVisibility(View.VISIBLE);
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
            }
        });

        getActivity().setTitle("태그별 보기");
        return layout;
    }

    public void loadData() {
        mData = new ArrayList<>();

        TagContentListItem item = new TagContentListItem();
        item.setSightName("tag6, 1, 3, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag1");
        item.addAttribute("subtag1");
        item.addAttribute("subtag3");
        item.addAttribute("subtag4");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag6, 3");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag1");
        item.addAttribute("subtag3");
        mData.add(item);


        item = new TagContentListItem();
        item.setSightName("tag2, 1, 2");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag2");
        item.addAttribute("subtag1");
        item.addAttribute("subtag2");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag2, 1");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag2");
        item.addAttribute("subtag1");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag3, 3, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag3");
        item.addAttribute("subtag3");
        item.addAttribute("subtag4");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag4, 1, 3");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag4");
        item.addAttribute("subtag1");
        item.addAttribute("subtag3");
        mData.add(item);

        item = new TagContentListItem();
        item.setSightName("tag5, 2, 4");
        item.setImage(R.drawable.page_search_icon);
        item.setLocationTypeTag("tag5");
        item.addAttribute("subtag2");
        item.addAttribute("subtag4");
        mData.add(item);
    }

    public void setOnChildButtonClick() {
        instance = SelectedTagInstance.getInstance(); // singleton 객체의 인스턴스를 받아옴.

        if(!instance.getSubtag().isEmpty()) { // 서브태그 목록이 비어 있지 않은 경우
            adapter.removeItem(); // 어댑터의 내용을 비움
            Log.d("LOG/PAGE_TAG", "adapter cleared.");
            for(TagContentListItem item : mData) {
                ArrayList<String> attribute = item.getAttribute();

                for(int i = 0; i < instance.getSubtag().size(); i++) { // 저장된 서브태그의 갯수만큼
                    if(attribute.contains(instance.getSubtag().get(i))) { // 서브태그가 포함되었는지 검사
                        adapter.addItem(item);
                        break;
                    }
                }
            }
        }

        adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);
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