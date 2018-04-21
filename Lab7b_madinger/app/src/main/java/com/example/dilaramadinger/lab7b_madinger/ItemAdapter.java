package com.example.dilaramadinger.lab7b_madinger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class ItemAdapter extends RealmBaseAdapter<ModelClass> implements ListAdapter {
    private MainActivity mainActivity;

    private static class ViewHolder {
        TextView itemName;
        TextView itemDetail;
        CheckBox done;
    }

    ItemAdapter(MainActivity activity, OrderedRealmCollection<ModelClass> data){
        super(data);
        this.mainActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.itemTextView);
            viewHolder.itemDetail = (TextView) convertView.findViewById(R.id.detailTextView);
            viewHolder.done = (CheckBox) convertView.findViewById(R.id.checkBox);
            viewHolder.done.setOnClickListener(listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(adapterData != null) {
            ModelClass model = adapterData.get(position);
            viewHolder.itemName.setText(model.getActivity_name());
            viewHolder.itemDetail.setText(model.getActivity_detail());
            viewHolder.done.setChecked(model.done());
            viewHolder.done.setTag(position);
        }
        return convertView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (adapterData != null) {
                ModelClass item = adapterData.get(position);
                mainActivity.changeItemDone(item.getId());
            }
        }
    };
}
