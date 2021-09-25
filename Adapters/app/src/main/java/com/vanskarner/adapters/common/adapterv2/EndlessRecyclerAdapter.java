package com.vanskarner.adapters.common.adapterv2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class EndlessRecyclerAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AdapterOperations.Endless, AdapterOperations.Add<T>, AdapterOperations.Change<T> {

    public static final int ITEM_VIEW = 0;
    public static final int PROGRESS_VIEW = 1;
    public boolean progressVisibility = false;
    protected List<T> list;

    public EndlessRecyclerAdapter(@NonNull List<T> list) {
        this.list = list;
    }

    protected abstract int setLoadLayout();

    protected abstract ItemViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent);

    protected abstract void bindItem(ItemViewHolder holder, T item, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return (viewType == ITEM_VIEW) ?
                setViewHolder(layoutInflater, parent) :
                new LoadViewHolder(layoutInflater
                        .inflate(setLoadLayout(), parent, false));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof LoadViewHolder)) {
            bindItem((ItemViewHolder) holder, list.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + (progressVisibility ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return position == list.size() ? PROGRESS_VIEW : ITEM_VIEW;
    }

    private static class LoadViewHolder extends RecyclerView.ViewHolder {
        private LoadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //AdapterOperations methods

    @Override
    public void addList(@NonNull List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void changeList(@NonNull List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressVisibility = true;
        notifyItemRangeChanged(list.size(), 1);
    }

    @Override
    public void hideProgress() {
        if (progressVisibility) {
            progressVisibility = false;
            notifyItemRangeChanged(list.size(), 1);
        }
    }

}
