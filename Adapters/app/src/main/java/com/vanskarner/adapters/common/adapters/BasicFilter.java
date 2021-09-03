package com.vanskarner.adapters.common.adapters;

import android.annotation.SuppressLint;
import android.widget.Filter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BasicFilter<T, S extends ViewHolder> extends Filter {

    private final List<T> list;
    private final List<T> originalList;
    private final RecyclerView.Adapter<S> adapter;
    private final Filtered<T> filtered;

    public BasicFilter(List<T> list,
                       List<T> originalList,
                       RecyclerView.Adapter<S> adapter,
                       Filtered<T> filtered) {
        this.list = list;
        this.originalList = originalList;
        this.adapter = adapter;
        this.filtered = filtered;
    }

    @Override
    public FilterResults performFiltering(CharSequence constraint) {
        List<T> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            String filterPatter = constraint.toString().toLowerCase().trim();
            for (T item : originalList) {
                if (filtered.filterCondition(item, filterPatter)) {
                    filteredList.add(item);
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = filteredList;
        return filterResults;
    }

    @SuppressLint("NotifyDataSetChanged")
    @SuppressWarnings("unchecked")
    @Override
    public void publishResults(CharSequence charSequence, FilterResults results) {
        list.clear();
        list.addAll((List<T>) results.values);
        adapter.notifyDataSetChanged();
    }

    interface Filtered<T> {
        boolean filterCondition(T item, String filterPattern);
    }

}
