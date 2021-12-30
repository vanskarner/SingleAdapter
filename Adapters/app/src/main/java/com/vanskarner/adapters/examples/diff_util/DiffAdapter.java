package com.vanskarner.adapters.examples.diff_util;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.singleadapter.BindAdapter;
import com.vanskarner.adapters.databinding.GridItemBinding;
import com.vanskarner.adapters.examples.WomanModel;

class DiffAdapter implements BindAdapter<WomanModel, DiffAdapter.DiffVH> {

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    @Override
    public void onBindViewHolder(DiffVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public DiffVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new DiffVH(GridItemBinding.inflate(inflater, parent, false));
    }

    static class DiffVH extends RecyclerView.ViewHolder {
        GridItemBinding binding;

        DiffVH(@NonNull GridItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
