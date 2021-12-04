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
    private List<AdapterItem> list;

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<AdapterItem> list) {
        this.list = list;
        notifyDataSetChanged();
        //notifyItemRangeChanged(lastPositionBefore + 1, listAdd.size());
    }

    public void addBindAdapter(BindAdapter delegateAdapter) {
        mapAdapter.put(delegateAdapter.getLayoutId(), delegateAdapter);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return Objects
                .requireNonNull(mapAdapter.get(viewType))
                .onCreateViewHolder(parent, LayoutInflater.from(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        getBinderAdapter(position).onBindViewHolder(list.get(position), holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getBinderAdapter(position).getLayoutId();
    }

    private BindAdapter getBinderAdapter(int position) {
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
