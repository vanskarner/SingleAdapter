package com.vanskarner.adapters.common.adaptersothers;

import java.util.List;

@SuppressWarnings("rawtypes")
class DefaultDiff<BI extends BindItem> extends BaseDiff<BI> {

    public DefaultDiff(List<BI> oldList) {
        super(oldList);
    }

    @Override
    public int getOldListSize() {
        return getOldList().size();
    }

    @Override
    public int getNewListSize() {
        return getNewList().size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        BI oldItem = getOldList().get(oldItemPosition);
        BI newItem = getNewList().get(newItemPosition);
        return oldItem.bindItemID().equals(newItem.bindItemID());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        BI oldItem = getOldList().get(oldItemPosition);
        BI newItem = getNewList().get(newItemPosition);
        return oldItem.equals(newItem);
    }

}
