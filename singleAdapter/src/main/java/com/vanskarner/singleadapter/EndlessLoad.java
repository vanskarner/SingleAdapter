package com.vanskarner.singleadapter;

import androidx.recyclerview.widget.AsyncListDiffer;

import java.util.ArrayList;
import java.util.List;

class EndlessLoad {

    private final AsyncListDiffer<BindItem> listDiffer;
    private final LoadAdapter adapter;
    private final LoadBindItem loadItem;

    public EndlessLoad(AsyncListDiffer<BindItem> listDiffer) {
        this.listDiffer = listDiffer;
        adapter = new LoadAdapter();
        loadItem = new LoadBindItem();
    }

    public LoadAdapter getAdapter() {
        return adapter;
    }

    public boolean isLoadInstance(BindItem item) {
        return item instanceof LoadBindItem;
    }

    public void setVisibleProgress(boolean isVisible) {
            List<BindItem> updateList = createItems();
            updateList.remove(loadItem);
            if (isVisible) {
                updateList.add(loadItem);
            }
            listDiffer.submitList(updateList);
    }

    private List<BindItem> createItems() {
        List<? extends BindItem> list = listDiffer.getCurrentList();
        return new ArrayList<>(list);
    }

}