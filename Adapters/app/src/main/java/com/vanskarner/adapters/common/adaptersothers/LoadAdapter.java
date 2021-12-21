package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class LoadAdapter implements OnCreateVH<LoadAdapter.LoadViewHolder> {
    private static final int DEFAULT_LAYOUT_ID = -1;

    private int layoutId = DEFAULT_LAYOUT_ID;
    private boolean visibleProgress;
    private final LoadBindItem item = new LoadBindItem();


    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void showProgress(AsyncListDiffer<BindItem> asyncListDiffer) {
        if (isEnableLoad() && !visibleProgress) {
            List<BindItem> newList = bindItems(asyncListDiffer);
            newList.add(item);
            asyncListDiffer.submitList(newList);
            visibleProgress = true;
        }
    }

    public void hideProgress(AsyncListDiffer<BindItem> asyncListDiffer) {
        if (isEnableLoad() && visibleProgress) {
            List<BindItem> newList = bindItems(asyncListDiffer);
            newList.remove(item);
            asyncListDiffer.submitList(newList);
            visibleProgress = false;
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
        LoadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private boolean isEnableLoad() {
        return layoutId != DEFAULT_LAYOUT_ID;
    }

    private List<BindItem> bindItems(AsyncListDiffer<BindItem> asyncListDiffer) {
        List<? extends BindItem> list = asyncListDiffer.getCurrentList();
        return new ArrayList<>(list);
    }

}
