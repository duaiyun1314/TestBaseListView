package com.andy.wang.baselistview.footmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.wang.baselistview.adapter.MultiTypeAdapter;


/**
 * Created by andy.wang on 2016/12/28.
 * <p/>
 */
public class RecyclerViewUtils {

    /**
     * 设置FooterView
     *
     * @param recyclerView
     * @param view
     */
    public static void setFooterView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof MultiTypeAdapter)) {
            return;
        }

        MultiTypeAdapter headerAndFooterAdapter = (MultiTypeAdapter) outerAdapter;
        headerAndFooterAdapter.setFooterView(view);
    }


}