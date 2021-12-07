package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

interface AdapterMethods{

    interface Basic<VH extends RecyclerView.ViewHolder> {
        VH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater);
    }

    interface Bind<VH extends RecyclerView.ViewHolder, M extends AdapterItem> {
        void onBindViewHolder(VH viewHolder, M item);
    }

}
