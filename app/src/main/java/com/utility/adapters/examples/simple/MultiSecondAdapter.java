package com.utility.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.databinding.GridItemBinding;
import com.utility.adapters.examples.WomanModel;
import com.vanskarner.singleadapter.BindAdapter;

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
    public Class<WomanModel> getClassItem() {
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
