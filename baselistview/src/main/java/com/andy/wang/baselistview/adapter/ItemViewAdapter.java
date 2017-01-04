package com.andy.wang.baselistview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by wanglu on 2017/1/3.
 */

public abstract class ItemViewAdapter<T, V extends RecyclerView.ViewHolder> {

    int position;

    @NonNull
    protected abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull V holder, @NonNull T t);

    public int getPosition() {
        return position;
    }
}
