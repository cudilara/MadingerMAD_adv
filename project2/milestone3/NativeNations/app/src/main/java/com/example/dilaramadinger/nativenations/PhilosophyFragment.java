package com.example.dilaramadinger.nativenations;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhilosophyFragment extends Fragment {


    public PhilosophyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_philosophy, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            ListView philosophyQuotes = (ListView) view.findViewById(R.id.PhilosophyList);
            ArrayAdapter<PhilosophyQuote> listAdapter = new ArrayAdapter<PhilosophyQuote>(getActivity(), android.R.layout.simple_list_item_1, PhilosophyQuote.quotes);
            //set the array adapter on the list view
            philosophyQuotes.setAdapter(listAdapter);
        }
    }

}
