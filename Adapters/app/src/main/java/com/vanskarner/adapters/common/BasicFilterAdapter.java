package com.vanskarner.adapters.common;

import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicFilterAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends BasicClickAdapter<T, ItemViewHolder>
        implements Filterable {

    protected List<T> originalList;
    protected Filter filter;

    protected BasicFilterAdapter(List<T> list) {
        super(list);
        this.originalList = new ArrayList<>(list);
    }

    protected abstract boolean filterCondition(T item, String filterPattern);

    protected Filter createFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<T> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(originalList);
                } else {
                    String filterPatter = constraint.toString().toLowerCase().trim();
                    for (T item : originalList) {
                        if (filterCondition(item, filterPatter)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<T>) results.values;
                notifyDataSetChanged();
            }
        };
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