package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
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
    private LoadAdapter loadAdapter = LoadAdapter.disabledLoadAdapter();
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

    public void add(LoadAdapter loadAdapter) {
        this.loadAdapter = loadAdapter;
    }

    public void add(BaseDiff diffUtilCallback) {
        this.defaultDiff = diffUtilCallback;
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
//        list.addAll(newList);
        list = newList;
        notifyItemRangeInserted(0, newList.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return loadAdapter.setLayoutId() == viewType ?
                loadAdapter.onCreateViewHolder(parent, inflater) :
                Objects.requireNonNull(mapAdapter.get(viewType))
                        .onCreateViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!loadAdapter.isVisibleProgress()) {
            filterBindAdapter(list.get(position)).onBindViewHolder(holder, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        boolean isVisibleProgress = loadAdapter.isVisibleProgress();
        return list.size() + (isVisibleProgress ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        boolean isVisibleProgress = position >= list.size();
        return isVisibleProgress ?
                loadAdapter.setLayoutId() :
                filterBindAdapter(list.get(position)).setLayoutId();
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

    private RuntimeException filterError(BindItem bindItem) {
        String messageError = "No BindAdapter added";
        if (!mapAdapter.isEmpty()) {
            messageError = "No BindAdapter added for item => "
                    + "[" + bindItem.getClass().getSimpleName() + "]";
        }
        return new RuntimeException(messageError);
    }

    private boolean isCorrectBindAdapter(BindAdapter bindAdapter, BindItem bindItem) {
        boolean isCorrectModel = bindAdapter.setModelClass().isInstance(bindItem);
        boolean isCorrectFilter = bindAdapter.filterItem(bindItem);
        return isCorrectModel && isCorrectFilter;
    }

}
