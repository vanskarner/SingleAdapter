package com.vanskarner.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.ItemGrid2Binding;
import com.vanskarner.adapters.examples.WomanModel;

class GridAdapter implements BindAdapter<WomanModel, GridAdapter.GridVH> {

    @Override
    public GridVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new GridVH(ItemGrid2Binding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(GridVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public int setLayoutId() {
        return R.layout.item_grid2;
    }

    @Override
    public Class<WomanModel> setModelClass() {
        return WomanModel.class;
    }

    static class GridVH extends RecyclerView.ViewHolder {
        ItemGrid2Binding binding;

        public GridVH(@NonNull ItemGrid2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
