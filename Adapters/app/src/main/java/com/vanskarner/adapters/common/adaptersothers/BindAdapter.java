package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

public interface BindAdapter<M extends BindItem, VH extends RecyclerView.ViewHolder>
        extends OnCreateVH<VH>, OnBindVH<M, VH> {

    Class<M> getModelItem();

    default boolean filter(M item) {
        return true;
    }

}
