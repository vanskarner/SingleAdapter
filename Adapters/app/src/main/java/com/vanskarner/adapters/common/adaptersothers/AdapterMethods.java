package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

interface AdapterMethods<M extends AdapterItem, VH extends RecyclerView.ViewHolder> {

    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater);

    void onBindViewHolder(M item, VH viewHolder);

}
