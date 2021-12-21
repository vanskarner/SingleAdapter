package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

interface OnBindVH<M extends BindItem, VH extends RecyclerView.ViewHolder> {

    void onBindViewHolder(VH viewHolder, M item);

}
