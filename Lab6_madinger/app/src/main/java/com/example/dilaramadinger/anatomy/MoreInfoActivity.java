package com.example.dilaramadinger.anatomy;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class MoreInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
