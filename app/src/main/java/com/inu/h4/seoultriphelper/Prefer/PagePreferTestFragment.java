package com.inu.h4.seoultriphelper.Prefer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.inu.h4.seoultriphelper.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PagePreferTestFragment extends Fragment {
    private ListView listView;                  // 출력을 위한 리스트 뷰 객체
    private ArrayList<String> data;             //  파일로부터 설문내용을 받아옴
    private PreferTestListViewAdapter adapter;      // 리스트 뷰 어댑터
    @Override
    public void onStart() {
        super.onStart();

        adapter = new PreferTestListViewAdapter();

        listView = (ListView) getView().findViewById(R.id.prefer_test_list);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        loadTestText();
        addListViewItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("사용자 성향");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_prefer_test, container, false);
    }

    public void loadTestText() {
        data = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.test);
        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while(true){
                String string= bufferedReader.readLine();
                if(string != null){
                    data.add(string);
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addListViewItem() {
        for(int i=0;i<data.size();i++) {
            adapter.addItem(data.get(i));
        }
    }
}
