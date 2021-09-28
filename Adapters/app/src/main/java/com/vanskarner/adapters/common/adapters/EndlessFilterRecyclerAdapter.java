package com.vanskarner.adapters.common.adapters;

import android.annotation.SuppressLint;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class EndlessFilterRecyclerAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends EndlessRecyclerAdapter<T, ItemViewHolder>
        implements BasicFilter.Filtered<T> {

    protected List<T> originalList;
    protected Filter filter;

    public EndlessFilterRecyclerAdapter(@NonNull List<T> list) {
        super(list);
        this.originalList = new ArrayList<>(list);
        this.filter = new BasicFilter<>(list, originalList, this, this);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    //AdapterOperations methods

    @Override
    public void addList(@NonNull List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            super.list.addAll(listAdd);
            originalList.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void changeList(@NonNull List<T> newList) {
        super.list.clear();
        super.list.addAll(newList);
        originalList.clear();
        originalList.addAll(newList);
        notifyDataSetChanged();
    }

}
