package com.example.dilaramadinger.lab7b_madinger;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        RealmResults<ModelClass> items = realm.where(ModelClass.class).findAll();

        final ItemAdapter adapter = new ItemAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
               LinearLayout layout = new LinearLayout(MainActivity.this);
               layout.setOrientation(LinearLayout.VERTICAL);

               final ModelClass item = (ModelClass) adapterView.getAdapter().getItem(i);

                // create edit text and add it to layout
               final EditText itemEditText = new EditText(MainActivity.this);
               itemEditText.setText(item.getActivity_name());
               layout.addView(itemEditText);
               final EditText detailEditText = new EditText(MainActivity.this);
               detailEditText.setText(item.getActivity_detail());
               layout.addView(detailEditText);

               AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
               dialog.setTitle("Edit Task");
               dialog.setView(layout);
               dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int i) {
                       final String updatedItemName = itemEditText.getText().toString();
                       final String updatedDetail = detailEditText.getText().toString();
                       if(!updatedItemName.isEmpty()){
                           changeItem(item.getId(), updatedItemName, updatedDetail);
                       }
                   }
               });
               dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i){
                       deleteItem(item.getId());
                   }
               });
               dialog.create();
               dialog.show();
           }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creates vertical linear layout to hold edit texts
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                // creates edit texts and adds to layout
                final EditText itemEditText = new EditText(MainActivity.this);
                itemEditText.setHint("Task");
                layout.addView(itemEditText);
                final EditText detailEditText = new EditText(MainActivity.this);
                detailEditText.setHint("Notes about this task");
                layout.addView(detailEditText);

                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Task");
                dialog.setView(layout);
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // gets item name entered
                        final String newItemName = itemEditText.getText().toString();
                        final String newItemDetail = detailEditText.getText().toString();

                        if(!newItemName.isEmpty()){
                            realm.executeTransactionAsync(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    ModelClass newItem = realm.createObject(ModelClass.class, UUID.randomUUID().toString());
                                    newItem.setActivity_name(newItemName);
                                    newItem.setActivity_detail(newItemDetail);
                                }
                            });
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close the Realm instance when the Activity exits
        realm.close();
    }

    public void changeItemDone(final String itemId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ModelClass item = realm.where(ModelClass.class).equalTo("id", itemId).findFirst();
                item.setDone(!item.done());
            }
        });
    }

    private void changeItem(final String itemId, final String firstField, final String secondField) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ModelClass item = realm.where(ModelClass.class).equalTo("id", itemId).findFirst();
                item.setActivity_name(firstField);
                item.setActivity_detail(secondField);
            }
        });

    }

    private void deleteItem(final String itemId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(ModelClass.class).equalTo("id", itemId)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
