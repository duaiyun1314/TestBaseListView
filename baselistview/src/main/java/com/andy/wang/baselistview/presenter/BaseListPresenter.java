package com.andy.wang.baselistview.presenter;

import android.content.Context;


import com.andy.wang.baselistview.footmanager.RecyclerFootManger;
import com.andy.wang.baselistview.view.BaseListView;

import java.util.List;


/**
 * Created by andy.wang on 2016/12/13.
 */

public abstract class BaseListPresenter extends BasePresenter<BaseListContract.IView> implements BaseListContract.IPresenter {


    public BaseListPresenter(Context context, BaseListContract.IView iView) {
        super(context, iView);
    }


    /**
     * change the footview status according to count of  fetching data
     *
     * @param isLoadNew
     * @param <T>
     * @return
     */
    protected <T> boolean updateFootAfterFetch(List<T> k, boolean isLoadNew) {
        if (k == null || k.size() == 0) {
            if (!isLoadNew) {
                iView.setFootStatus(RecyclerFootManger.STATUS_END, true);
                return false;
            } else {
                iView.setFootStatus(RecyclerFootManger.STATUS_NORMAL, true);
                return true;
            }
        }

        if (k.size() < BaseListView.PAGE_COUNT_LIMIT) {
            iView.setFootStatus(RecyclerFootManger.STATUS_END, true);
            return true;
        } else {
            iView.setFootStatus(RecyclerFootManger.STATUS_LOADING, true);
            return true;
        }
    }
}
