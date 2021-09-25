package com.vanskarner.adapters.common.adapterv2;

import android.annotation.SuppressLint;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BasicFilter<T, S extends ViewHolder> extends Filter {

    private final List<T> list;
    private final List<T> originalList;
    private final RecyclerView.Adapter<S> adapter;
    private final Filtered<T> filtered;

    public BasicFilter(@NonNull List<T> list,
                       @NonNull List<T> originalList,
                       @NonNull RecyclerView.Adapter<S> adapter,
                       @NonNull Filtered<T> filtered) {
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
                if (filtered.onFilterCondition(item, filterPatter)) {
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

    public interface Filtered<T> extends Filterable {

        boolean onFilterCondition(T item, String filterPattern);

    }

}
