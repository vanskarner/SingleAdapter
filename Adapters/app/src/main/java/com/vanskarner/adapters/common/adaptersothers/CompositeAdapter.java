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

    private final Map<Integer, BindAdapter<AdapterItem, RecyclerView.ViewHolder>> mapAdapter = new HashMap<>();
    private LoadAdapter loadAdapter = LoadAdapter.disabledLoadAdapter();
    private List<AdapterItem> list;


    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<AdapterItem> list) {
        this.list = list;
        notifyDataSetChanged();
        //notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
    }

    public void addAdapter(BindAdapter delegateAdapter) {
        mapAdapter.put(delegateAdapter.getLayoutId(), delegateAdapter);
    }

    public void addAdapter(LoadAdapter loadAdapter) {
        this.loadAdapter = loadAdapter;
    }

    public void showProgress() {
        loadAdapter.showProgress(this, list);
    }

    public void hideProgress() {
        loadAdapter.hideProgress(this, list);
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
        filterBinderAdapter(position).onBindViewHolder(holder, list.get(position));
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
                loadAdapter.getLayoutId() :
                filterBinderAdapter(position).getLayoutId();
    }

    private BindAdapter filterBinderAdapter(int position) {
        for (Map.Entry<Integer, BindAdapter<AdapterItem, RecyclerView.ViewHolder>> entry :
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
        boolean isCorrectModel = bindAdapter.getModelClass().isInstance(item);
        boolean isCorrectFilter = bindAdapter.filterItem(item);
        return isCorrectModel && isCorrectFilter;
    }

}
