package com.inu.h4.seoultriphelper.Setting;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.inu.h4.seoultriphelper.R;

public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_setting);
    }
}
