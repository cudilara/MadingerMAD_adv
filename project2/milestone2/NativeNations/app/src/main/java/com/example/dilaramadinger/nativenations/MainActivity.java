package com.example.dilaramadinger.nativenations;

//resources:
// philosophy icon: https://pixabay.com/en/culture-dream-catcher-feather-1297313/
// language icon: https://pixabay.com/en/balloons-comic-talk-bubble-1187260/
// record icon: https://pixabay.com/en/microphone-audio-music-listening-159768/
// pow wow icon: http://co-nnect.me/american-indian-quilts/american-indian-quilt-cover-american-indian-quilt-blocks-native-american-quilts-for-sale-native-american-fancy-shawl-quilt/
// all navigation icons: https://icons8.com

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.philosophy_nav:
                    mTextMessage.setText("philosophy");
                    // Listview with existing phrases
                    // Floating button or button on top to add a quote
                    // Screen or pop up to add or edit quote
                    // Add realm to make persistent
                    return true;
                case R.id.language_nav:
                    // DO THIS SECTION THIRD
                    // Make buttons with each leading to another a activity for that language
                    // Each language activity has a listview and the way to add another phrase
                    // reuse code from philosophy to manage language lists
                    // add realm
                    mTextMessage.setText("language");
                    return true;
                case R.id.record_nav:
                    // DO THIS SECTION LAST
                    mTextMessage.setText("record");
                    // button to record speech
                    // button to record video
                    // store recordings in realm
                    // button leading to listview with recorded stuff
                    return true;
                case R.id.pow_wow_nav:
                    mTextMessage.setText("Pow wow");
                    // listview with PowWow locations
                    // each item leads to more info -> website for that pow wow
                    // button leading to PowWow ettiquette
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
