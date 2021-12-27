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
            List<BindItem> updateList = bindItems();
            updateList.add(item);
            listDiffer.submitList(updateList);
            visibleProgress = true;
        }
    }

    public void hideProgress() {
        if (isEnableLoad() && visibleProgress) {
            List<BindItem> updateList = bindItems();
            updateList.remove(item);
            listDiffer.submitList(updateList);
            visibleProgress = false;
        }
    }

    private boolean isEnableLoad() {
        return adapter.getLayoutId() != DEFAULT_LAYOUT_ID;
    }

    private List<BindItem> bindItems() {
        List<? extends BindItem> list = listDiffer.getCurrentList();
        return new ArrayList<>(list);
    }

}
