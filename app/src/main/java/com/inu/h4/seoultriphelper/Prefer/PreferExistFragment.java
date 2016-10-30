package com.inu.h4.seoultriphelper.Prefer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inu.h4.seoultriphelper.DataRepository;
import com.inu.h4.seoultriphelper.InnerDBHelper;
import com.inu.h4.seoultriphelper.InnerDBHelper2;
import com.inu.h4.seoultriphelper.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PreferExistFragment extends Fragment {
    Bundle bundle = null;
    Button reexamineButton;
    TextView preferTextContent, preferTextTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("사용자 성향");

        return inflater.inflate(R.layout.prefer_full, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        InnerDBHelper2 innerDBHelper2 = new InnerDBHelper2(getContext(), "PREFERDB2.db",null,1);

        preferTextContent = (TextView) getActivity().findViewById(R.id.prefer_result_text_content);
        preferTextTitle = (TextView) getActivity().findViewById(R.id.prefer_result_text_title);
        bundle = getArguments();
        if(bundle != null) {
            if(innerDBHelper2.selectPrefer() == null) {        // 기존의 설문 결과가 없을 경우.
                Log.d("LOG/PREFER", innerDBHelper2.selectPrefer() + "aaa");
                innerDBHelper2.insertPrefer((String) bundle.get("preferIndex"));        // 방금 완성한 설문의 결과를 저장.
                Log.d("LOG/PREFER", innerDBHelper2.selectPrefer() + "bbb");
                bundle.remove("preferIndex");       // 번들에 저장해놓은 결과는 삭제. (글로벌 설문 결과 변수가 존재하므로.)
            }
        }
        setPreferTextValue(innerDBHelper2.selectPrefer());         // 설문 결과를 화면에 출력.

        reexamineButton = (Button) getActivity().findViewById(R.id.btn_prefer_reexamine);
        reexamineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PreferTestFirstFragment()).addToBackStack(null).commit();
            }
        });
    }


    // 설문 결과(index)를 바탕으로, raw 폴더에서 적절한 값을 가져와서 화면에 출력함.
    public void setPreferTextValue(String index) {
        InputStream is = getResources().openRawResource(R.raw.prefer_result);
        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while(true){
                String string= bufferedReader.readLine();
                if(string != null){
                    if(string.equals(index)) {
                        preferTextTitle.setText(bufferedReader.readLine());
                        preferTextContent.setText(bufferedReader.readLine());
                    }
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}