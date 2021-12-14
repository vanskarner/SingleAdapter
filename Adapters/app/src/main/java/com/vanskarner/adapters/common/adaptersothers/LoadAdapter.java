package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("rawtypes")
class LoadAdapter implements BasicAdapter<LoadAdapter.LoadViewHolder> {
    private static final int DEFAULT_LAYOUT_ID = -1;

    private int layoutId = DEFAULT_LAYOUT_ID;
    private boolean visibleProgress;

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void showProgress(RecyclerView.Adapter adapter, int listSize) {
        if (isEnableLoad() && !visibleProgress) {
            visibleProgress = true;
            adapter.notifyItemInserted(listSize);
        }
    }

    public void hideProgress(RecyclerView.Adapter adapter, int listSize) {
        if (isEnableLoad() && visibleProgress) {
            visibleProgress = false;
            adapter.notifyItemRemoved(listSize);
        }
    }

    public boolean isVisibleProgress() {
        return visibleProgress;
    }

    @Override
    public LoadAdapter.LoadViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         LayoutInflater inflater) {
        return new LoadViewHolder(inflater.inflate(layoutId, parent, false));
    }

    static class LoadViewHolder extends RecyclerView.ViewHolder {
        public LoadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private boolean isEnableLoad() {
        return layoutId != DEFAULT_LAYOUT_ID;
    }

}
