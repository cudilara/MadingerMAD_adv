package com.example.dilaramadinger.nativenations;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhilosophyFragment extends Fragment {
    private Realm realm;


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
//            ListView philosophyQuotes = (ListView) view.findViewById(R.id.PhilosophyList);
//            ArrayAdapter<PhilosophyQuote> listAdapter = new ArrayAdapter<PhilosophyQuote>(getActivity(), android.R.layout.simple_list_item_1, PhilosophyQuote.quotes);
//            //set the array adapter on the list view
//            philosophyQuotes.setAdapter(listAdapter);

            getActivity().setTitle("Wisdom Quotes");

            realm = Realm.getDefaultInstance();
            RealmResults<PhilosophyQuote> quotes = realm.where(PhilosophyQuote.class).findAll();
            final PhilAdapter adapter = new PhilAdapter(this, quotes);
            ListView listView = (ListView) view.findViewById(R.id.PhilosophyList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    LinearLayout layout = new LinearLayout(getActivity());
                    layout.setOrientation(LinearLayout.VERTICAL);
                    final PhilosophyQuote quote = (PhilosophyQuote) adapterView.getAdapter().getItem(i);

                    //edit texts
                    final EditText quoteEditText = new EditText(getActivity());
                    quoteEditText.setText(quote.getQuote());
                    layout.addView(quoteEditText);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Edit Quote");
                    dialog.setView(layout);
                    dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final String updatedQuote = quoteEditText.getText().toString();
                            if(!updatedQuote.isEmpty()){
                                changeQuote(quote.getId(), updatedQuote);
                            }
                        }
                    });
                    dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteQote(quote.getId());
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
            });

            FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout layout = new LinearLayout(getActivity());
                    layout.setOrientation(LinearLayout.VERTICAL);

                    final EditText quoteEditText = new EditText(getActivity());
                    quoteEditText.setHint("quote");
                    layout.addView(quoteEditText);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Add Quote");
                    dialog.setView(layout);
                    dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String newQuote = quoteEditText.getText().toString();
                            if(!newQuote.isEmpty()){
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        PhilosophyQuote newquote = realm.createObject(PhilosophyQuote.class, UUID.randomUUID().toString());
                                        newquote.setQuote(newQuote);
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


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void changeQuote(final String quoteId, final String quoteText) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PhilosophyQuote quote = realm.where(PhilosophyQuote.class).equalTo("id", quoteId).findFirst();
                quote.setQuote(quoteText);
            }
        });
    }

    private void deleteQote(final String quoteId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(PhilosophyQuote.class).equalTo("id", quoteId)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }

}
