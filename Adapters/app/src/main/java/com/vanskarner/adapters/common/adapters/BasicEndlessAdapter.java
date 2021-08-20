package com.vanskarner.adapters.common.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BasicEndlessAdapter<T, ItemViewHolder extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    protected List<T> list;
    protected View.OnClickListener onItemClickListener;

    public BasicEndlessAdapter(List<T> list) {
        this.list = list;
    }

    protected abstract int setItemLayout();

    protected abstract int setLoadLayout();

    protected abstract ItemViewHolder createViewHolder(View view);

    protected abstract void bindItem(ItemViewHolder holder, T item, int position);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return (viewType == VIEW_TYPE_ITEM) ?
                createViewHolder(layoutInflater
                        .inflate(setItemLayout(), parent, false)) :
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
        return (list == null) ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private static class LoadViewHolder extends RecyclerView.ViewHolder {
        private LoadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //Custom methods

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateList(List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addList(List<T> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
        }
    }

    public void showProgress() {
        if (getItemCount() == 0) {
            list.add(null);
            notifyItemRangeChanged(0, 1);
        } else if (list.get(getItemCount() - 1) != null) {
            list.add(null);
            notifyItemRangeChanged(getItemCount() - 1, 1);
        }
    }

    public void hideProgress() {
        if (getItemCount() > 0 && list.get(getItemCount() - 1) == null) {
            list.remove(getItemCount() - 1);
            notifyItemRangeChanged(getItemCount(), 1);
        }
    }

}