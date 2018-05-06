package com.example.dilaramadinger.madingerfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class ItemAdapter extends RealmBaseAdapter<ModelClass> implements ListAdapter {
    private SecondActivity secondActivity;

    private static class ViewHolder {
        TextView itemName;
        TextView itemUrl;
    }

    ItemAdapter(SecondActivity activity, OrderedRealmCollection<ModelClass> data){
        super(data);
        this.secondActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.oneRowText);
//            viewHolder.itemUrl = (TextView) convertView.findViewById(R.id.detailTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(adapterData != null) {
            ModelClass model = adapterData.get(position);
            viewHolder.itemName.setText(model.getActivity_name());
//            viewHolder.itemUrl.setText(model.getActivity_detail());
        }
        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                ModelClass item = adapterData.get(position);
            }
        }
    };
}
