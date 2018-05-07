package com.example.dilaramadinger.madingerfinal;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getActionBar();

        // action bar comes as null
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
