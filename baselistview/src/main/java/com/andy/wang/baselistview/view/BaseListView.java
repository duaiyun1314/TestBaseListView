package com.andy.wang.baselistview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.andy.wang.baselistview.R;
import com.andy.wang.baselistview.adapter.MultiTypeAdapter;
import com.andy.wang.baselistview.footmanager.RecyclerFootManger;
import com.andy.wang.baselistview.presenter.BaseListContract;
import com.andy.wang.baselistview.presenter.BaseListPresenter;
import com.andy.wang.baselistview.typepool.TypePool;

import java.util.List;


public abstract class BaseListView<P extends BaseListPresenter> extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener, BaseListContract.IView, RecyclerFootManger.LoadNextListener {

    protected RecyclerView mRecyclerView;
    private MultiTypeAdapter mLineAdpater;
    private SwipeRefreshLayout mRefreshLayout;
    protected P mPresenter;
    protected RecyclerFootManger footManger;
    public static final int PAGE_COUNT_LIMIT = 20;

    public BaseListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseListView(Context context) {
        this(context, null);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.layout_base_list, this);
        initView();
        mPresenter = createPresenter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.content_view);
        mLineAdpater = new MultiTypeAdapter(null, createTypePool());
        mLineAdpater.setEmptyView(configEmptyView());
        mRecyclerView.setLayoutManager(generateLayoutManager());
        mRecyclerView.setAdapter(mLineAdpater);
        if (createFootView()) {
            footManger = new RecyclerFootManger(getContext(), mRecyclerView);
            footManger.setLoadNextListner(this);
        }

        //init refresh layout
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        this.mRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.RED);
        this.mRefreshLayout.setDistanceToTriggerSync(150);
        this.mRefreshLayout.setProgressViewOffset(true, 0, 100);
    }

    protected abstract TypePool createTypePool();

    protected View configEmptyView() {
        View emptyContent = LayoutInflater.from(getContext()).inflate(R.layout.layout_common_empty, null);
        return emptyContent;
    }

    protected RecyclerView.LayoutManager generateLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    protected abstract P createPresenter();

    @Override
    public void update(Object... args) {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void updateData(List<Object> items) {
        //加载完数据需调用该方法
        mRefreshLayout.setRefreshing(false);
        mLineAdpater.setData(items);
    }

    @Override
    public void updateDataAdd(List<Object> items) {
        //加载完数据需调用该方法
        mRefreshLayout.setRefreshing(false);
        mLineAdpater.addData(items);
    }

    /**
     * Whether need to load more
     *
     * @return
     */
    protected boolean createFootView() {
        return false;
    }

    @Override
    public void setFootStatus(final int status, final boolean showView) {
        post(new Runnable() {
            @Override
            public void run() {
                footManger.setState(status, showView);
            }
        });
    }

}
