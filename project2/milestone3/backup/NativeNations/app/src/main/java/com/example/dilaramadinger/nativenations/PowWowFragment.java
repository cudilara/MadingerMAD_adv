package com.example.dilaramadinger.nativenations;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PowWowFragment extends Fragment {


    public PowWowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pow_wow, container, false);
    }

//    static final String[] URLS = new String[] {}; Can put urls here

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            getActivity().setTitle("Pow Wow Info");

            ListView myList = (ListView) view.findViewById(R.id.PowWowList);
            ArrayAdapter<PowwowEvent> listAdapter = new ArrayAdapter<PowwowEvent>(getActivity(), android.R.layout.simple_list_item_1, PowwowEvent.locations);
            //set the array adapter on the list view
            myList.setAdapter(listAdapter);

            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PowwowEvent selectedUrl = PowwowEvent.locations[position];
                    Uri uri = Uri.parse(selectedUrl.getWebsite());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
    }

}
