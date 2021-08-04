package com.vanskarner.adapters.common;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BasicClickViewHolder extends RecyclerView.ViewHolder {

    protected abstract void setupView(View itemView);

    public BasicClickViewHolder(@NonNull View itemView, View.OnClickListener onItemClickListener) {
        super(itemView);
        setupView(itemView);
        itemView.setTag(this);
        itemView.setOnClickListener(onItemClickListener);
    }
}
