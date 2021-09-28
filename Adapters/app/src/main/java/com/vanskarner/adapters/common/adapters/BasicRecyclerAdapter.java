package com.vanskarner.adapters.common.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BasicRecyclerAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<ItemViewHolder>
        implements AdapterOperations.Add<T>, AdapterOperations.Change<T> {

    protected List<T> list;

    public BasicRecyclerAdapter(@NonNull List<T> list) {
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
        return list.size();
    }

    //AdapterOperations methods

    @Override
    public void addList(@NonNull List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void changeList(@NonNull List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

}