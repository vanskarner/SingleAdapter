package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("rawtypes")
public interface BindAdapter<M extends BindItem, VH extends RecyclerView.ViewHolder>
        extends OnCreateVH<VH>, OnBindVH<M, VH> {

    Class<M> getModelClass();

    default boolean filter(M item) {
        return true;
    }

}
