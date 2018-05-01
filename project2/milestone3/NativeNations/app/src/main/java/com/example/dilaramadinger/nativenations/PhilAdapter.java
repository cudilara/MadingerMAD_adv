package com.example.dilaramadinger.nativenations;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class PhilAdapter extends RealmBaseAdapter<PhilosophyQuote> implements ListAdapter {
    private PhilosophyFragment myFrag;

    private static class ViewHolder {
        TextView quoteTxt;
    }

    PhilAdapter(PhilosophyFragment activity, OrderedRealmCollection<PhilosophyQuote> data){
        super(data);
        this.myFrag = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.phil_one_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.quoteTxt = (TextView) convertView.findViewById(R.id.itemTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(adapterData != null) {
            PhilosophyQuote model = adapterData.get(position);
            viewHolder.quoteTxt.setText(model.getQuote());
        }
        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                PhilosophyQuote item = adapterData.get(position);
            }
        }
    };
}
