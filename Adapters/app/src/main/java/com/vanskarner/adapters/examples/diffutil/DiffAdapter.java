package com.vanskarner.adapters.examples.diffutil;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.ItemGrid2Binding;
import com.vanskarner.adapters.examples.WomanModel;

class DiffAdapter implements BindAdapter<WomanModel, DiffAdapter.DiffVH> {

    @Override
    public Class<WomanModel> getModelClass() {
        return WomanModel.class;
    }

    @Override
    public void onBindViewHolder(DiffVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public DiffVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new DiffVH(ItemGrid2Binding.inflate(inflater, parent, false));
    }

    static class DiffVH extends RecyclerView.ViewHolder {
        ItemGrid2Binding binding;

        public DiffVH(@NonNull ItemGrid2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
