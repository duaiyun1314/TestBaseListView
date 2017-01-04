package com.andy.wanglu.testbaselistview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andy.wang.baselistview.adapter.ItemViewAdapter;

/**
 * Created by wanglu on 2017/1/4.
 */

public class PeopleItemAdapter extends ItemViewAdapter<People, PeopleItemAdapter.PeopleViewHolder> {
    @NonNull
    @Override
    protected PeopleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull PeopleViewHolder holder, @NonNull People people) {
        holder.peopleName.setText(people.getName());
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {

        private TextView peopleName;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            peopleName = (TextView) itemView.findViewById(R.id.people_name);
        }
    }
}
