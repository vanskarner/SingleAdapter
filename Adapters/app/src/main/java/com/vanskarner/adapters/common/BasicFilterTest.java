package com.vanskarner.adapters.common;

import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicFilterTest<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends BasicClickAdapter<T, ItemViewHolder>
        implements Filterable, FilterCustom.Filtered<T> {

    protected List<T> originalList;
    protected Filter filter;

    protected BasicFilterTest(List<T> list) {
        super(list);
        this.originalList = new ArrayList<>(list);
    }

    protected Filter createFilter() {
        return new FilterCustom<>(list, originalList, this, this);
    }

    //Custom methods

    @Override
    public void updateList(List<T> newList) {
        this.list.clear();
        this.list.addAll(newList);
        this.originalList.clear();
        this.originalList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void addList(List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            originalList.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = createFilter();
        }
        return filter;
    }

}