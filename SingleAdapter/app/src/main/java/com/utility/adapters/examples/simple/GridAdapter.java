package com.utility.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.singleadapter.BindAdapter;
import com.utility.adapters.databinding.GridItemBinding;
import com.utility.adapters.examples.WomanModel;

class GridAdapter implements BindAdapter<WomanModel, GridAdapter.GridVH> {

    @Override
    public GridVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new GridVH(GridItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(GridVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class GridVH extends RecyclerView.ViewHolder {
        GridItemBinding binding;

        GridVH(@NonNull GridItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
