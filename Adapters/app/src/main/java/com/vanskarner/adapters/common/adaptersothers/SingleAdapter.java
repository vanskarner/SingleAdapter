package com.vanskarner.adapters.common.adaptersothers;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, BindAdapter<RecyclerView.ViewHolder, BindItem>> mapAdapter = new HashMap<>();
    private LoadAdapter loadAdapter = LoadAdapter.disabledLoadAdapter();
    private List<BindItem> list;

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<BindItem> list) {
        this.list = list;
        notifyItemRangeInserted(0, list.size());
        hideProgress();
    }

    public void addList(@NonNull List<BindItem> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
            hideProgress();
        }
    }

    public void add(BindAdapter bindAdapter) {
        mapAdapter.put(bindAdapter.setLayoutId(), bindAdapter);
    }

    public void add(LoadAdapter loadAdapter) {
        this.loadAdapter = loadAdapter;
    }

    public void showProgress() {
        loadAdapter.showProgress(this, list.size());
    }

    public void hideProgress() {
        loadAdapter.hideProgress(this, list.size());
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

    private BindAdapter<RecyclerView.ViewHolder, BindItem> filterBindAdapter(BindItem bindItem) {
        for (Map.Entry<Integer, BindAdapter<RecyclerView.ViewHolder, BindItem>> entry :
                mapAdapter.entrySet()) {
            if (isCorrectBindAdapter(entry.getValue(), bindItem)) {
                return entry.getValue();
            }
        }
        throw filterError(bindItem);
    }

    public RuntimeException filterError(BindItem bindItem) {
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
