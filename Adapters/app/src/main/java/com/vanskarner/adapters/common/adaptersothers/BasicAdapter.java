package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

interface BasicAdapter<VH extends RecyclerView.ViewHolder> {

    VH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater);

}
