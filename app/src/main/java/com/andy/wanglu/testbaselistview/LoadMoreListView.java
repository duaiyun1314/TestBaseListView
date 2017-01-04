package com.andy.wanglu.testbaselistview;

import android.content.Context;
import android.util.AttributeSet;

import com.andy.wang.baselistview.typepool.MultiTypePool;
import com.andy.wang.baselistview.typepool.TypePool;
import com.andy.wang.baselistview.view.BaseListView;

/**
 * Created by wanglu on 2017/1/4.
 */

public class LoadMoreListView extends BaseListView<LoadMorePresenter> {
    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected TypePool createTypePool() {
        MultiTypePool pool = new MultiTypePool();
        pool.register(People.class, new PeopleItemAdapter());
        pool.register(Gender.class, new GenderItemAdapter());
        return pool;
    }

    @Override
    protected LoadMorePresenter createPresenter() {
        return new LoadMorePresenter(getContext(), this);
    }

    @Override
    public void loadNext() {
        mPresenter.loadNext();
    }

    @Override
    protected boolean createFootView() {
        return true;
    }

    @Override
    public void update(Object... args) {
        super.update(args);
        mPresenter.loadNew();
    }
}
