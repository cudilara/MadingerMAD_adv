package com.example.dilaramadinger.anatomy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            //check: override added automatically
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                String parttype = (String) listView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, BodyCategoryActivity.class);
                intent.putExtra("bodytype", parttype);
                startActivity(intent);
            }
        };
        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setOnItemClickListener(itemClickListener);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.create_order:
                Intent intent = new Intent(this, MoreInfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
