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

public class GenderItemAdapter extends ItemViewAdapter<Gender, GenderItemAdapter.GenderViewHolder> {
    @NonNull
    @Override
    protected GenderViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_item_gender, parent, false);
        return new GenderViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull GenderViewHolder holder, @NonNull Gender gender) {
        holder.genderName.setText(gender.getGender());

    }

    static class GenderViewHolder extends RecyclerView.ViewHolder {
        private TextView genderName;

        public GenderViewHolder(View itemView) {
            super(itemView);
            genderName = (TextView) itemView.findViewById(R.id.gender_name);
        }
    }
}
