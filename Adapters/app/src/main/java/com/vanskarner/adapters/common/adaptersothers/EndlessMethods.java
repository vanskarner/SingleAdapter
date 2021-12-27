package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.AsyncListDiffer;

import java.util.ArrayList;
import java.util.List;

class EndlessMethods {
    private static final int DEFAULT_LAYOUT_ID = -1;

    private boolean visibleProgress;
    private final LoadAdapter adapter;
    private final LoadBindItem item;
    private final AsyncListDiffer<BindItem> listDiffer;

    public EndlessMethods(AsyncListDiffer<BindItem> listDiffer) {
        this.listDiffer = listDiffer;
        adapter = new LoadAdapter(DEFAULT_LAYOUT_ID);
        item = new LoadBindItem();
    }

    public int getLayoutId() {
        return adapter.getLayoutId();
    }

    public void setLayoutId(int layoutId) {
        adapter.setLayoutId(layoutId);
    }

    public LoadAdapter getAdapter() {
        return adapter;
    }

    public void showProgress() {
        if (isEnableLoad() && !visibleProgress) {
            List<BindItem> newList = bindItems(listDiffer);
            newList.add(item);
            listDiffer.submitList(newList);
            visibleProgress = true;
        }
    }

    public void hideProgress() {
        if (isEnableLoad() && visibleProgress) {
            List<BindItem> newList = bindItems(listDiffer);
            newList.remove(item);
            listDiffer.submitList(newList);
            visibleProgress = false;
        }
    }

    private boolean isEnableLoad() {
        return adapter.getLayoutId() != DEFAULT_LAYOUT_ID;
    }

    private List<BindItem> bindItems(AsyncListDiffer<BindItem> asyncListDiffer) {
        List<? extends BindItem> list = asyncListDiffer.getCurrentList();
        return new ArrayList<>(list);
    }

}
