package com.vanskarner.adapters.ui.simple_search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanskarner.adapters.R;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vanskarner.adapters.common.adapterv2.FilterRecyclerAdapter;
import com.vanskarner.adapters.databinding.ItemSimpleBinding;
import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

class SimpleSearchAdapter extends
        FilterRecyclerAdapter<PersonModel, SimpleSearchAdapter.ItemViewHolder> {

    private View.OnClickListener onItemClickListener;

    public SimpleSearchAdapter(List<PersonModel> list) {
        super(list);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected ItemViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        ItemSimpleBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_simple, parent, false);
        return new ItemViewHolder(binding, onItemClickListener);
    }

    @Override
    protected void bindItem(ItemViewHolder holder, PersonModel item, int position) {
        holder.binding.setPerson(item);
    }

    @Override
    public boolean onFilterCondition(PersonModel item, String filterPattern) {
        return item.getName().toLowerCase().contains(filterPattern);
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemSimpleBinding binding;

        public ItemViewHolder(ItemSimpleBinding binding,
                              View.OnClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onItemClickListener);
        }

    }
}
