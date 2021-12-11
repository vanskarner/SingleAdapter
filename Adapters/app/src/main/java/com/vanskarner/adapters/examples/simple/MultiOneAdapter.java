package com.vanskarner.adapters.examples.simple;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.ItemSimple2Binding;

class MultiOneAdapter implements BindAdapter<WomanModel, MultiOneAdapter.MultiOneVH> {

    @Override
    public MultiOneVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return new MultiOneVH(ItemSimple2Binding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(MultiOneVH viewHolder, WomanModel item) {
        viewHolder.binding.setWoman(item);
    }

    @Override
    public int setLayoutId() {
        return R.layout.item_simple2;
    }

    @Override
    public boolean filterItem(WomanModel item) {
        return item.getType() == WomanModel.Type.ONE;
    }

    @Override
    public Class<WomanModel> setModelClass() {
        return WomanModel.class;
    }

    static class MultiOneVH extends RecyclerView.ViewHolder {
        ItemSimple2Binding binding;

        public MultiOneVH(@NonNull ItemSimple2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
