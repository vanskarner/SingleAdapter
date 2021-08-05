package com.vanskarner.adapters.common;

import android.widget.Filter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

public class FilterCustom<T,S extends RecyclerView.ViewHolder,ItemA extends Adapter<S>>
        extends Filter {

    private final List<T> list;
    private final List<T> originalList;
    private final ItemA adapter;
    private final Filtered<T> tFiltered;

    public FilterCustom(List<T> list, List<T> originalList, ItemA adapter, Filtered<T> tFiltered) {
        this.list = list;
        this.originalList = originalList;
        this.adapter = adapter;
        this.tFiltered = tFiltered;
    }

    @Override
    public FilterResults performFiltering(CharSequence constraint) {
        List<T> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            String filterPatter = constraint.toString().toLowerCase().trim();
            for (T item : originalList) {
                if (tFiltered.filterCondition(item, filterPatter)) {
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
    public void publishResults(CharSequence charSequence, FilterResults results) {
        list.clear();
        list.addAll((List<T>) results.values);
        adapter.notifyDataSetChanged();
    }
    
    public interface Filtered<T>{
        boolean filterCondition(T item, String filterPatter);
    }

}
