package com.vanskarner.singleadapter.util;

import androidx.annotation.NonNull;

import com.vanskarner.singleadapter.BaseDiffCallback;

public class ExampleDiffCallback extends BaseDiffCallback<ExampleModel> {
    @Override
    public boolean areItemsTheSame(@NonNull ExampleModel oldItem, @NonNull ExampleModel newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull ExampleModel oldItem, @NonNull ExampleModel newItem) {
        return false;
    }
}
