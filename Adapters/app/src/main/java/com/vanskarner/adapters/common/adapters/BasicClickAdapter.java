package com.vanskarner.adapters.common.adapters;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BasicClickAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends BasicAdapter<T, ItemViewHolder> {

    protected View.OnClickListener onItemClickListener;

    public BasicClickAdapter(List<T> list) {
        super(list);
    }

    //Custom methods

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}