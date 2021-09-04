package com.vanskarner.adapters.common.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BasicAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<ItemViewHolder> {

    protected List<T> list;

    public BasicAdapter(List<T> list) {
        this.list = list;
    }

    protected abstract ItemViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent,
                                                    int viewType);

    protected abstract void bindItem(ItemViewHolder holder, T item, int position);

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return setViewHolder(layoutInflater, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        bindItem(viewHolder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //Custom methods

    @SuppressLint("NotifyDataSetChanged")
    public void changeList(@NonNull List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addList(@NonNull List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

}