package com.andy.wang.baselistview.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.wang.baselistview.typepool.GlobalMultiTypePool;
import com.andy.wang.baselistview.typepool.MultiTypePool;
import com.andy.wang.baselistview.typepool.TypePool;

import java.util.List;

/**
 * Created by wanglu on 2017/1/3.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Object> items;
    @NonNull
    protected TypePool delegate;
    @Nullable
    protected LayoutInflater inflater;

    private View emptyView;
    private View footerView;

    private static final int EMPTY_VIEW = -1;
    private static final int FOOTER_VIEW = -2;

    public MultiTypeAdapter(@NonNull List<Object> items) {
        this(items, new MultiTypePool());
    }


    public MultiTypeAdapter(@NonNull List<Object> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity));
    }


    public MultiTypeAdapter(
            List<Object> items, @NonNull TypePool delegate) {
        this.items = items;
        this.delegate = delegate;
    }


    @SuppressWarnings("unchecked")
    @Override
    public int getItemViewType(int position) {
        if (items == null || items.size() == 0) {
            return EMPTY_VIEW;
        } else if (position < items.size()) {
            Object item = items.get(position);
            return delegate.indexOf(item.getClass());
        } else {
            return FOOTER_VIEW;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            emptyView.setLayoutParams(lp);
            return new EmptyViewHolder(emptyView);
        } else if (viewType == FOOTER_VIEW) {
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(lp);
            return new FooterViewHolder(footerView);
        } else {
            /*RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);*/

            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            return delegate.getProviderByIndex(viewType).onCreateViewHolder(inflater, parent);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) >= 0) {
            Object item = items.get(position);
            ItemViewAdapter provider = delegate.getProviderByClass(item.getClass());
            provider.position = holder.getAdapterPosition();
            provider.onBindViewHolder(holder, item);
        }
    }


    @Override
    public int getItemCount() {
        return items == null || items.size() == 0 ? (emptyView == null ? 0 : 1) : items.size() + (footerView == null ? 0 : 1);
    }

    /**
     * 如果设置了全局typepool需要调用此方法
     */
    public void applyGlobalMultiTypePool() {
        for (int i = 0; i < GlobalMultiTypePool.getContents().size(); i++) {
            final Class<?> clazz = GlobalMultiTypePool.getContents().get(i);
            final ItemViewAdapter provider = GlobalMultiTypePool.getProviders().get(i);
            if (!this.delegate.getContents().contains(clazz)) {
                this.delegate.register(clazz, provider);
            }
        }
    }


    public void setEmptyView(View view) {
        emptyView = view;
    }

    public void setFooterView(View view) {
        footerView = view;
    }

    public void setData(List<Object> list) {
        items = list;
        super.notifyDataSetChanged();
    }

    public void addData(List<Object> list) {
        items.addAll(list);
        super.notifyDataSetChanged();
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
