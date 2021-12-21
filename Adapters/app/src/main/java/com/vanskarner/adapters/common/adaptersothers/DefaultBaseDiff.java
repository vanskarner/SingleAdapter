package com.vanskarner.adapters.common.adaptersothers;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

class DefaultBaseDiff extends BaseDiffCallback<BindItem> {

    @Override
    public boolean areItemsTheSame(@NonNull BindItem oldItem, @NonNull BindItem newItem) {
        return areContentsTheSame(oldItem, newItem);
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull BindItem oldItem, @NonNull BindItem newItem) {
        return oldItem.equals(newItem);
    }

}
