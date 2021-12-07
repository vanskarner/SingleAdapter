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
public class CompositeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, BindAdapter<RecyclerView.ViewHolder, AdapterItem>> mapAdapter = new HashMap<>();
    private LoadAdapter loadAdapter = LoadAdapter.disabledLoadAdapter();
    private List<AdapterItem> list;


    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<AdapterItem> list) {
        this.list = list;
        notifyItemRangeInserted(0, list.size());
        hideProgress();
    }

    public void addList(@NonNull List<AdapterItem> listAdd) {
        if (listAdd.size() > 0) {
            int lastPositionBefore = getItemCount() - 1;
            list.addAll(listAdd);
            notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
            hideProgress();
        }
    }

    public void add(BindAdapter delegateAdapter) {
        mapAdapter.put(delegateAdapter.setLayoutId(), delegateAdapter);
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
            filterBindAdapter(position).onBindViewHolder(holder, list.get(position));
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
                filterBindAdapter(position).setLayoutId();
    }

    private BindAdapter<RecyclerView.ViewHolder, AdapterItem> filterBindAdapter(int position) {
        for (Map.Entry<Integer, BindAdapter<RecyclerView.ViewHolder, AdapterItem>> entry :
                mapAdapter.entrySet()) {
            if (isCorrectBindAdapter(entry.getValue(), position)) {
                return entry.getValue();
            }
        }
        String messageError = "AdapterItem => "
                + "[" + list.get(position).getClass().getSimpleName() + "]"
                + "does not have a BindAdapter assigned";
        throw new RuntimeException(messageError);
    }

    private boolean isCorrectBindAdapter(BindAdapter bindAdapter, int position) {
        AdapterItem item = list.get(position);
        boolean isCorrectModel = bindAdapter.setModelClass().isInstance(item);
        boolean isCorrectFilter = bindAdapter.filterItem(item);
        return isCorrectModel && isCorrectFilter;
    }

}
