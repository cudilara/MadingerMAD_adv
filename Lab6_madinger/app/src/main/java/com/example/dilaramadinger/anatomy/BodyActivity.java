package com.example.dilaramadinger.anatomy;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.content.Intent;

public class BodyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        int bodynum = (Integer)getIntent().getExtras().get("partid");
        BodyPart part = BodyPart.head[bodynum];
        ImageView partImage = (ImageView)findViewById(R.id.bodyImageView);
        partImage.setImageResource(part.getImageResourceID());
        TextView partName = (TextView)findViewById(R.id.part_name);
        partName.setText(part.getName());
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
