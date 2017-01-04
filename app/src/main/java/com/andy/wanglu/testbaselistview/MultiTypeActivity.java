package com.andy.wanglu.testbaselistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.wang.baselistview.adapter.MultiTypeAdapter;
import com.andy.wang.baselistview.typepool.MultiTypePool;
import com.andy.wang.baselistview.typepool.TypePool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu on 2017/1/4.
 */

public class MultiTypeActivity extends Activity {
    private RecyclerView recyclerView;
    private MultiTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multitype);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new MultiTypeAdapter(null, createTypePool());
        adapter.setEmptyView(View.inflate(this,R.layout.layout_common_empty,null));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Object> datas = new ArrayList<>();
                datas.add(new Gender("male"));
                datas.add(new People("kebi"));
                datas.add(new People("james"));
                datas.add(new People("wade"));
                datas.add(new Gender("famale"));
                datas.add(new People("郭美美"));
                datas.add(new People("张燕"));
                adapter.setData(datas);
            }
        }, 3000);
    }

    private TypePool createTypePool() {
        MultiTypePool pool = new MultiTypePool();
        pool.register(People.class, new PeopleItemAdapter());
        pool.register(Gender.class, new GenderItemAdapter());
        return pool;
    }
}
