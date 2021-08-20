package com.vanskarner.adapters.common.adapters;

import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomEndlessAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends BasicEndlessAdapter<T, ItemViewHolder>
        implements Filterable, BasicFilter.Filtered<T> {

    protected List<T> originalList;
    protected Filter filter;

    public CustomEndlessAdapter(List<T> list) {
        super(list);
        this.originalList = new ArrayList<>(list);
    }

    protected Filter createFilter() {
        return new BasicFilter<>(list, originalList, this, this);
    }

    //Custom methods

    @Override
    public void updateList(List<T> newList) {
        super.list.clear();
        super.list.addAll(newList);
        originalList.clear();
        originalList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void addList(List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            super.list.addAll(listAdd);
            originalList.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    @Override
    public Filter getFilter() {
        return (filter == null) ? filter = createFilter() : filter;
    }

}