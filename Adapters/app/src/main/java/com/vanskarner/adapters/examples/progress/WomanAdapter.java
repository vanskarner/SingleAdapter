package com.vanskarner.adapters.examples.progress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.examples.simple.WomanModel;

class WomanAdapter implements BindAdapter<WomanModel, WomanAdapter.WomanVH> {

    @Override
    public WomanVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        return null;
        /*return new WomanVH(ItemSimple2Binding.inflate(inflater, parent, false));*/
    }

    @Override
    public void onBindViewHolder(WomanVH viewHolder, WomanModel item) {
        /*viewHolder.binding.setWoman(item);*/
    }

    @Override
    public int setLayoutId() {
        return R.layout.item_simple2;
    }

    @Override
    public Class<WomanModel> setModelClass() {
        return WomanModel.class;
    }

    static class WomanVH extends RecyclerView.ViewHolder {
        public WomanVH(@NonNull View itemView) {
            super(itemView);
        }
        /*ItemSimple2Binding binding;*/

        /*public WomanVH(@NonNull ItemSimple2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }*/
    }

}
