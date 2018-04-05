package com.example.dilaramadinger.anatomy;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
            default:
                listAdapter = new ArrayAdapter<BodyPart>(this, android.R.layout.simple_list_item_1, BodyPart.head);
        }
        listParts.setAdapter(listAdapter);
    }
}
