package com.example.dilaramadinger.nativenations;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {


    public LanguageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            ListView listLocation = (ListView) view.findViewById(R.id.languageListView);

            ArrayAdapter<Language> listAdapter = new ArrayAdapter<Language>(getActivity(), android.R.layout.simple_list_item_1, Language.langList);
            //set the array adapter on the list view
            listLocation.setAdapter(listAdapter);
        }
    }

}
