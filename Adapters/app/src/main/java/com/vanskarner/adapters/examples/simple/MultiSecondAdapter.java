package com.vanskarner.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.GridItemBinding;
import com.vanskarner.adapters.examples.WomanModel;

class MultiSecondAdapter implements BindAdapter<WomanModel, MultiSecondAdapter.SecondVH> {

    @Override
    public SecondVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new SecondVH(GridItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(SecondVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public Class<WomanModel> getModelClass() {
        return WomanModel.class;
    }

    @Override
    public boolean filter(WomanModel item) {
        return item.getType() == WomanModel.Type.SECOND;
    }

    static class SecondVH extends RecyclerView.ViewHolder {
        GridItemBinding binding;

        SecondVH(@NonNull GridItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
