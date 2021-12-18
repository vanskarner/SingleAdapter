package com.vanskarner.adapters.common.adaptersothers;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

class DefaultBaseDiff<BI extends BindItem> extends BaseDiffCallback<BI> {
    @Override
    public boolean areItemsTheSame(@NonNull BI oldItem, @NonNull BI newItem) {
        return areContentsTheSame(oldItem, newItem);
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull BI oldItem, @NonNull BI newItem) {
        return oldItem.equals(newItem);
    }

}
