package com.utility.adapters.singleadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

interface OnCreateVH<VH extends RecyclerView.ViewHolder> {

    VH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater);

}
