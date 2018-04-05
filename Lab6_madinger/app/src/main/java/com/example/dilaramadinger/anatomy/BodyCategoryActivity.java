package com.example.dilaramadinger.anatomy;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.view.Menu;

public class BodyCategoryActivity extends ListActivity {
    private String bodytype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        bodytype = i.getStringExtra("bodytype");
        ListView listParts = getListView();
        ArrayAdapter<BodyPart> listAdapter;
        switch(bodytype){
            case "Head":
                listAdapter = new ArrayAdapter<BodyPart>(this, android.R.layout.simple_list_item_1, BodyPart.head);
                break;
            case "Torso":
                listAdapter = new ArrayAdapter<BodyPart>(this, android.R.layout.simple_list_item_1, BodyPart.torso);
                break;
            case "Extremities":
                listAdapter = new ArrayAdapter<BodyPart>(this, android.R.layout.simple_list_item_1, BodyPart.extremities);
                break;
            default:
                listAdapter = new ArrayAdapter<BodyPart>(this, android.R.layout.simple_list_item_1, BodyPart.head);
        }
        listParts.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        Intent intent = new Intent(BodyCategoryActivity.this, BodyActivity.class);
//        String parttype = (String) listView.getItemAtPosition(position);
        intent.putExtra("partid", (int)id);
        startActivity(intent);
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
