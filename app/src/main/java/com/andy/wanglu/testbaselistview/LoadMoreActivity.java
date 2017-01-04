package com.andy.wanglu.testbaselistview;

import android.app.Activity;
import android.os.Bundle;

import com.andy.wang.baselistview.view.BaseListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu on 2017/1/4.
 */

public class LoadMoreActivity extends Activity {
    private LoadMoreListView baseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        baseListView = (LoadMoreListView) findViewById(R.id.recycler_view);
        baseListView.update();
    }

}
