package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

interface OnBindVH<BI extends BindItem, VH extends RecyclerView.ViewHolder> {

    void onBindViewHolder(VH viewHolder, BI item);

}
