package com.inu.h4.seoultriphelper.Detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.inu.h4.seoultriphelper.R;

public class SightDetailReviewDialog extends Dialog {

    float rating;
    String writer;
    String title;
    String content;

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public SightDetailReviewDialog(Context context, float rating) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.rating = rating;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.7f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.detail_review_dialog);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.detail_review_dialog_rating);
        final EditText writerView = (EditText) findViewById(R.id.detail_review_dialog_writer);
        final EditText titleView = (EditText) findViewById(R.id.detail_review_dialog_title);
        final EditText contentView = (EditText) findViewById(R.id.detail_review_dialog_content);
        Button submitButton = (Button) findViewById(R.id.btn_detail_review_dialog_submit);

        // 별점 세팅.
        ratingBar.setRating(rating);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 값들 받아옴.
                writer = writerView.getText().toString();
                title = titleView.getText().toString();
                content = contentView.getText().toString();

            }
        });
    }
}

