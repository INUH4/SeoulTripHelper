package com.inu.h4.seoultriphelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PageBucketEmptyActivity extends AppCompatActivity {
    Button AddBucketWithSearchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucket_empty);

        AddBucketWithSearchButton = (Button) findViewById(R.id.bt_bucket);
        AddBucketWithSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageBucketEmptyActivity.this, PageSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
