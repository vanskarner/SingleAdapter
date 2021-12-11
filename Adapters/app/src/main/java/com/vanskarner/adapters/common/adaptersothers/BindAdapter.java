package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("rawtypes")
public interface BindAdapter<M extends BindItem, VH extends RecyclerView.ViewHolder>
        extends BasicAdapter<VH> {

    void onBindViewHolder(VH viewHolder, M item);

    int setLayoutId();

    Class<M> setModelClass();

    default boolean filterItem(M item) {
        return true;
    }

}
