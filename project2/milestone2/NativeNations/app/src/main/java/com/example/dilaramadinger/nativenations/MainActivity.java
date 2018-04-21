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
                    return true;
                case R.id.language_nav:
                    mTextMessage.setText("language");
                    return true;
                case R.id.record_nav:
                    mTextMessage.setText("record");
                    return true;
                case R.id.pow_wow_nav:
                    mTextMessage.setText("Pow wow");
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
