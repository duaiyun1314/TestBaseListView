package com.andy.wanglu.testbaselistview;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.andy.wang.baselistview.presenter.BaseListContract;
import com.andy.wang.baselistview.presenter.BaseListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu on 2017/1/4.
 */

public class LoadMorePresenter extends BaseListPresenter {
    private int currentCount;
    private int totalCount = 74;

    public LoadMorePresenter(Context context, BaseListContract.IView iView) {
        super(context, iView);
    }

    @Override
    public void loadNew(Object... args) {
        List<Object> datas = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            datas.add(new People("测试" + i));
        }
        currentCount = 20;
        updateFootAfterFetch(datas, true);
        iView.updateData(datas);

    }

    @Override
    public void refresh() {
        loadNew();
    }

    @Override
    public void loadNext(Object... args) {
        List<Object> datas = new ArrayList<>();
        int current = currentCount;
        for (int i = current + 1; i <= current + 20; i++) {
            if (i > totalCount) break;
            currentCount = i;
            datas.add(new People("测试" + i));
        }
        updateFootAfterFetch(datas, false);
        iView.updateDataAdd(datas);
    }
}
