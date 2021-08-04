package com.vanskarner.adapters.common;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BasicEndlessAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends BasicFilterAdapter<T, ItemViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    protected BasicEndlessAdapter(List<T> list) {
        super(list);
    }

    protected abstract int setLoadLayout();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = (viewType == VIEW_TYPE_ITEM) ? setItemLayout() : setLoadLayout();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(layout, parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        if (list.get(position) != null) {
            Log.d("Endless", "list." + list.size());
            bindItem(viewHolder, list.get(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    //Custom methods

    public void showProgress() {
        if (getItemCount() == 0) {
            list.add(null);
            notifyItemRangeChanged(0, 1);
        } else if (list.get(getItemCount() - 1) != null) {
            list.add(null);
            notifyItemRangeChanged(getItemCount() - 1, 1);
        }
    }

    public void hideProgress() {
        if (getItemCount() > 0 && list.get(getItemCount() - 1) == null) {
            list.remove(getItemCount() - 1);
            notifyItemRangeChanged(getItemCount(), 1);
        }
    }

}