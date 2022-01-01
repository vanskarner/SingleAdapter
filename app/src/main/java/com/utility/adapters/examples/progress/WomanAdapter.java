package com.utility.adapters.examples.progress;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utility.adapters.singleadapter.BindAdapter;
import com.utility.adapters.databinding.GridItemBinding;
import com.utility.adapters.examples.WomanModel;

class WomanAdapter implements BindAdapter<WomanModel, WomanAdapter.WomanVH> {

    @Override
    public WomanVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new WomanVH(GridItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(WomanVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class WomanVH extends RecyclerView.ViewHolder {
        GridItemBinding binding;

        public WomanVH(@NonNull GridItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
