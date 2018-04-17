package com.example.dilaramadinger.space;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpaceListFragment  extends Fragment implements AdapterView.OnItemClickListener{


    public SpaceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_space_list, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            //load data into fragment
            //get the list view
            ListView listCategory = (ListView) view.findViewById(R.id.listView);
            //define an array adapter
            ArrayAdapter<SpacePerson> listAdapter = new ArrayAdapter<SpacePerson>(getActivity(), android.R.layout.simple_list_item_1, SpacePerson.spacepeople);
            //set the array adapter on the list view
            listCategory.setAdapter(listAdapter);
            //attach the listener to the listview
            listCategory.setOnItemClickListener(this);
        }
    }

    //create interface
    interface SpaceListListener {
        void itemClicked(long id);
    }

    //create listener
    private SpaceListListener listener;

    @Override public void onAttach(Activity context){
        super.onAttach(context);
        //attaches the context to the listener
        listener = (SpaceListListener) context;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        if (listener != null){
            //tells the listener an item was clicked
            listener.itemClicked(id);
        }
    }

}
