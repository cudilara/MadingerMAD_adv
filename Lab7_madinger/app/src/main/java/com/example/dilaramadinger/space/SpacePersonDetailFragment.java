package com.example.dilaramadinger.space;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpacePersonDetailFragment extends Fragment implements View.OnClickListener {
    private ArrayAdapter<String> adapter;
    private long categoryId;

    public SpacePersonDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState !=null){
            categoryId = savedInstanceState.getLong("categoryId");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_space_person_detail, container, false);
    }

    @Override public void onStart(){
        super.onStart();

        View view = getView();
        ListView listPeople = (ListView) view.findViewById(R.id.personlistView);

        // get hero data
        ArrayList<String> personlist = new ArrayList<String>();
        personlist = SpacePerson.spacepeople[(int) categoryId].getSpacepersons();

        //set array adapter
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, personlist);

        //bind array adapter to the list view
        listPeople.setAdapter(adapter);

        Button addHeroButton = (Button) view.findViewById(R.id.addPersonButton);
        addHeroButton.setOnClickListener(this);

        //register context menu
        registerForContextMenu(listPeople);
    }

    public void setUniverse(long id){
        this.categoryId = id;
    }

    @Override public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("categoryId", categoryId);
    }

    interface ButtonClickListener{
        void addpersonclicked(View view);
    }

    private ButtonClickListener listener;

    @Override public void onAttach(Context context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (ButtonClickListener)context;
    }

    @Override public void onClick(View view){
        if (listener !=null){
            listener.addpersonclicked(view);
        }
    }

    public void addperson(){
        //create alert dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        //create edit text
        final EditText edittext = new EditText(getActivity());

        //add edit text to dialog
        dialog.setView(edittext);

        //set dialog title
        dialog.setTitle("Add Space Person");

        //sets OK action
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //get hero name entered
                String personName = edittext.getText().toString();
                if(!personName.isEmpty()){
                    // add hero
                    SpacePerson.spacepeople[(int) categoryId].getSpacepersons().add(personName);
                    //refresh the list view
                    SpacePersonDetailFragment.this.adapter.notifyDataSetChanged();
                }
            }
        });

        //sets cancel action
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // cancel
            }
        });

        //present alert dialog
        dialog.show();
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        //cast ContextMenu.ContextMenuInfo to AdapterView.AdapterContextMenuInfo since we're using an adapter
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //get person name that was pressed
        String personname = adapter.getItem(adapterContextMenuInfo.position);
        //set the menu title
        menu.setHeaderTitle("Delete " + personname);
        //add the choices to the menu
        menu.add(1, 1, 1, "Yes");
        menu.add(2, 2, 2, "No");
    }

    @Override public boolean onContextItemSelected(MenuItem item){
        //get the id of the item
        int itemId = item.getItemId();
        if (itemId == 1) { //if yes menu item was pressed
            //get the position of the menu item
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //remove the person
            SpacePerson.spacepeople[(int) categoryId].getSpacepersons().remove(info.position);
            //refresh the list view
            SpacePersonDetailFragment.this.adapter.notifyDataSetChanged();
        }
        return true;
    }

}
