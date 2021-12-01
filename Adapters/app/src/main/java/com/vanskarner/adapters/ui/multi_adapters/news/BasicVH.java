package com.vanskarner.adapters.ui.multi_adapters.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BasicVH<T> extends RecyclerView.ViewHolder {

    public BasicVH(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T model);

}
