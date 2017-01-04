package com.andy.wang.baselistview.presenter;

import java.util.List;

/**
 * Created by andy on 16-6-6.
 */
public class BaseListContract {
    public interface IView {

        void update(Object... args);

        void updateData(List<Object> items);

        void updateDataAdd(List<Object> items);

        void setFootStatus(int status, boolean showView);


    }

    public interface IPresenter<T> {
        void loadNew(Object... args);

        void refresh();

        void loadNext(Object... args);

    }
}
