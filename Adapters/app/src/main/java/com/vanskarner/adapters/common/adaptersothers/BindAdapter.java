package com.vanskarner.adapters.common.adaptersothers;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BindAdapter<M extends AdapterItem, VH extends RecyclerView.ViewHolder>
        implements AdapterMethods.Basic<VH>, AdapterMethods.Bind<VH, M> {

    public abstract int getLayoutId();

    public abstract Class<M> getModelClass();

    public boolean filterItem(M item) {
        return true;
    }

}
