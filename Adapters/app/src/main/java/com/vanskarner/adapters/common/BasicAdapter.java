package com.vanskarner.adapters.common;

import android.view.LayoutInflater;
import android.view.View;
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

    protected abstract int setItemLayout();

    protected abstract ItemViewHolder createViewHolder(View view);

    protected abstract void bindItem(ItemViewHolder holder, T item, int position);

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(setItemLayout(), parent, false);
        return createViewHolder(view);
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

    public void updateList(List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addList(List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

}