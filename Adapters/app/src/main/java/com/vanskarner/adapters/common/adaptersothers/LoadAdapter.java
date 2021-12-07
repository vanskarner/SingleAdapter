package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("rawtypes")
public class LoadAdapter implements BasicAdapter<LoadAdapter.LoadViewHolder> {
    private static final int DEFAULT_LAYOUT_ID = -1;

    private final int layoutId;
    private boolean enableLoad = true;
    private boolean visibleProgress;

    public LoadAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    private LoadAdapter(int layoutId, boolean enableLoad) {
        this.layoutId = layoutId;
        this.enableLoad = enableLoad;
    }

    protected void showProgress(RecyclerView.Adapter adapter, int listSize) {
        if (enableLoad) {
            visibleProgress = true;
            adapter.notifyItemRangeChanged(listSize, 1);
        }
    }

    protected void hideProgress(RecyclerView.Adapter adapter, int listSize) {
        if (enableLoad && visibleProgress) {
            visibleProgress = false;
            adapter.notifyItemRangeChanged(listSize, 1);
        }
    }

    public boolean isVisibleProgress() {
        return visibleProgress;
    }

    public int setLayoutId() {
        return layoutId;
    }

    protected static LoadAdapter disabledLoadAdapter() {
        return new LoadAdapter(DEFAULT_LAYOUT_ID, false);
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

}
