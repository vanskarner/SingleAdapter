package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DelegateAdapter<M extends AdapterItem, VH extends RecyclerView.ViewHolder> {
    private final Class<M> modelClass;

    public DelegateAdapter(Class<M> modelClass) {
        this.modelClass = modelClass;
    }

    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater);

    public abstract int getLayoutId();

    public abstract void onBindViewHolder(M modeClass, VH viewHolder);

    public Class<M> getModelClass() {
        return modelClass;
    }
}
