package com.inu.h4.seoultriphelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PagePlannerEmptyActivity extends AppCompatActivity {
    Button SubmitBucketButton,SubmitSearchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_planner_empty);

        SubmitBucketButton = (Button) findViewById(R.id.btn_submit_bucket);
        SubmitBucketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (true) {              // 장바구니가 비어있을 경우
                    intent = new Intent(PagePlannerEmptyActivity.this, PageBucketEmptyActivity.class);
                } else {                // 장바구니가 비어있지 않을 경우
                    intent = new Intent(PagePlannerEmptyActivity.this, PageBucketExistActivity.class);
                }
                if (intent != null)
                    startActivity(intent);
            }
        });

        SubmitSearchButton = (Button) findViewById(R.id.btn_submit_search);
        SubmitSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagePlannerEmptyActivity.this, PageSearchActivity.class);
                startActivity(intent);
            }
        });

    }
}
