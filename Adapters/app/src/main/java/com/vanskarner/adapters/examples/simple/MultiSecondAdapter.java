package com.vanskarner.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.ItemGrid2Binding;
import com.vanskarner.adapters.examples.WomanModel;

class MultiSecondAdapter implements BindAdapter<WomanModel, MultiSecondAdapter.SecondVH> {

    @Override
    public SecondVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new SecondVH(ItemGrid2Binding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(SecondVH viewHolder, WomanModel item) {
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

    @Override
    public boolean filterItem(WomanModel item) {
        return item.getType() == WomanModel.Type.SECOND;
    }

    static class SecondVH extends RecyclerView.ViewHolder {
        ItemGrid2Binding binding;

        public SecondVH(@NonNull ItemGrid2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
