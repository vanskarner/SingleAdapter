package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> mapAdapter = new HashMap<>();
    private List<? extends BindItem> list = Collections.emptyList();//private List<? extends BindItem> list = Collections.emptyList();
    private final LoadAdapter loadAdapter = new LoadAdapter();
    private BaseDiff defaultDiff = new DefaultDiff(list);

/*    public void setList(@NonNull final List<? extends BindItem> newList) {
        defaultDiff.setNewList(newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(defaultDiff);
        *//*list.clear();
        list.addAll(newList);*//*
        list = newList;
        diffResult.dispatchUpdatesTo(this);
        hideProgress();
    }*/

    public void add(BindAdapter bindAdapter) {
        mapAdapter.put(bindAdapter.setLayoutId(), bindAdapter);
    }

    public void add(BaseDiff diffUtilCallback) {
        this.defaultDiff = diffUtilCallback;
    }

    public void add(int idLoadLayout) {
        loadAdapter.setLayoutId(idLoadLayout);
    }

    public void showProgress() {
        loadAdapter.showProgress(this, list.size());
    }

    public void hideProgress() {
        loadAdapter.hideProgress(this, list.size());
    }

    public void changeList(List<? extends BindItem> newList) {
        int currentListSize = list.size();
        list.clear();
        notifyItemRangeRemoved(0, currentListSize);
        list = newList;//list.addAll(newList);
        notifyItemRangeInserted(0, newList.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return loadAdapter.getLayoutId() == viewType ?
                loadAdapter.onCreateViewHolder(parent, inflater) :
                Objects.requireNonNull(mapAdapter.get(viewType))
                        .onCreateViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isProgressInactive(position)) {
            filterBindAdapter(list.get(position)).onBindViewHolder(holder, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + (loadAdapter.isVisibleProgress() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return isProgressInactive(position) ?
                filterBindAdapter(list.get(position)).setLayoutId() :
                loadAdapter.getLayoutId();
    }

    private boolean isProgressInactive(int position) {
        return position < list.size();
    }

    private BindAdapter<BindItem, RecyclerView.ViewHolder> filterBindAdapter(BindItem bindItem) {
        for (Map.Entry<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> entry :
                mapAdapter.entrySet()) {
            if (isCorrectBindAdapter(entry.getValue(), bindItem)) {
                return entry.getValue();
            }
        }
        throw filterError(bindItem);
    }

    private boolean isCorrectBindAdapter(BindAdapter bindAdapter, BindItem bindItem) {
        boolean isCorrectModel = bindAdapter.setModelClass().isInstance(bindItem);
        boolean isCorrectFilter = bindAdapter.filterItem(bindItem);
        return isCorrectModel && isCorrectFilter;
    }

    private RuntimeException filterError(BindItem bindItem) {
        String messageError = "No BindAdapter added";
        if (!mapAdapter.isEmpty()) {
            messageError = "No BindAdapter added for item => "
                    + "[" + bindItem.getClass().getSimpleName() + "]";
        }
        return new RuntimeException(messageError);
    }

}
