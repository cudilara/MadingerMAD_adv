package com.example.dilaramadinger.lab8_madinger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String received = "";
        String toDisplay = "";

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String title = extras.getString("category");

        String number = extras.getString("number");

        for(int i = 0; i < Integer.parseInt(number); i++){
            received = extras.getString("here" + i);
            toDisplay = toDisplay + '\n' + received;
        }

        TextView myText = (TextView)findViewById(R.id.textView);
        myText.setText(toDisplay);

        TextView myTitle = (TextView)findViewById(R.id.title);
        myTitle.setText(title);
    }
}
