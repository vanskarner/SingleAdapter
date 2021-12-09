package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class BaseDiff<BI extends BindItem> extends DiffUtil.Callback {
    private final List<BI> oldList;
    private List<BI> newList;

    public BaseDiff(List<BI> oldList) {
        this.oldList = oldList;
    }

    public void setNewList(List<BI> newList) {
        this.newList = newList;
    }

    public List<BI> getOldList() {
        return oldList;
    }

    public List<BI> getNewList() {
        return newList;
    }
}
