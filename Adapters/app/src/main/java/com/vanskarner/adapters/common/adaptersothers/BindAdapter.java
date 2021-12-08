package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

public interface BindAdapter<VH extends RecyclerView.ViewHolder, M extends BindItem>
        extends BasicAdapter<VH> {

    void onBindViewHolder(VH viewHolder, M item);

    int setLayoutId();

    Class<M> setModelClass();

    default boolean filterItem(M item) {
        return true;
    }

}
