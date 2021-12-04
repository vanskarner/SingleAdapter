package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BindAdapter<M extends AdapterItem, VH extends RecyclerView.ViewHolder>
        implements AdapterMethods<M, VH> {

    public abstract int getLayoutId();

    public abstract Class<M> getModelClass();

    public boolean filterItem(M item) {
        return true;
    }

}
