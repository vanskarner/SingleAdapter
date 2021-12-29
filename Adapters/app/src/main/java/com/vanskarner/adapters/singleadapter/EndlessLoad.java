package com.vanskarner.adapters.singleadapter;

import androidx.recyclerview.widget.AsyncListDiffer;

import java.util.ArrayList;
import java.util.List;

class EndlessLoad {
    private static final int DEFAULT_LAYOUT_ID = -1;

    private final AsyncListDiffer<BindItem> listDiffer;
    private final LoadAdapter adapter;
    private final LoadBindItem loadItem;
    private boolean visibleProgress;

    public EndlessLoad(AsyncListDiffer<BindItem> listDiffer) {
        this.listDiffer = listDiffer;
        adapter = new LoadAdapter(DEFAULT_LAYOUT_ID);
        loadItem = new LoadBindItem();
    }

    public LoadAdapter getAdapter() {
        return adapter;
    }

    public void setProgressFalse() {
        visibleProgress = false;
    }

    public boolean isLoadInstance(BindItem item) {
        return item instanceof LoadBindItem;
    }

    public void showProgress() {
        if (isEnableLoad() && !visibleProgress) {
            List<BindItem> updateList = createItems();
            updateList.add(loadItem);
            listDiffer.submitList(updateList);
            visibleProgress = true;
        }
    }

    public void hideProgress() {
        if (isEnableLoad() && visibleProgress) {
            List<BindItem> updateList = createItems();
            updateList.remove(loadItem);
            listDiffer.submitList(updateList);
            visibleProgress = false;
        }
    }

    private boolean isEnableLoad() {
        return adapter.getLayoutId() != DEFAULT_LAYOUT_ID;
    }

    private List<BindItem> createItems() {
        List<? extends BindItem> list = listDiffer.getCurrentList();
        return new ArrayList<>(list);
    }

}
