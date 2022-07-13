package com.vanskarner.singleadapter;

import androidx.recyclerview.widget.RecyclerView;

public interface BindAdapter<BI extends BindItem, VH extends RecyclerView.ViewHolder>
        extends OnCreateVH<VH>, OnBindVH<BI, VH> {

    Class<BI> getClassItem();

    default boolean filter(BI item) {
        return true;
    }

}
