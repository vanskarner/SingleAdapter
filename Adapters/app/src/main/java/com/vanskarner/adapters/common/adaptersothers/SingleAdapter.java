package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> mapAdapter = new HashMap<>();
    private List<? extends BindItem> list = Collections.emptyList();
    private final LoadAdapter loadAdapter = new LoadAdapter();
    private BaseDiff defaultDiff = new DefaultDiff(list);

    public void setList(@NonNull final List<? extends BindItem> newList) {
        /*defaultDiff.setNewList(new ArrayList(newList));
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(defaultDiff);
        list.clear();
        list = newList;//list.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
        hideProgress();*/
        hideProgress();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return list.size();
            }

            @Override
            public int getNewListSize() {
                return newList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return list.get(oldItemPosition).bindItemID().equals(newList.get(newItemPosition).bindItemID());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return list.get(oldItemPosition).equals(newList.get(newItemPosition));
            }
        });
        this.list.clear();
        this.list = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    public void add(@NonNull final BindAdapter bindAdapter) {
        int classHashCode = bindAdapter.getClass().hashCode();
        mapAdapter.put(classHashCode, bindAdapter);
    }

    public void add(@NonNull final BaseDiff diffUtilCallback) {
        this.defaultDiff = diffUtilCallback;
    }

    public void add(final int idLoadLayout) {
        loadAdapter.setLayoutId(idLoadLayout);
    }

    public void showProgress() {
        loadAdapter.showProgress(this, list.size());
    }

    public void hideProgress() {
        loadAdapter.hideProgress(this, list.size());
    }

    public void changeList(List<? extends BindItem> newList) {
        hideProgress();
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
            filterMap(list.get(position)).getValue().onBindViewHolder(holder, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + (loadAdapter.isVisibleProgress() ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return isProgressInactive(position) ?
                filterMap(list.get(position)).getKey() :
                loadAdapter.getLayoutId();
    }

    private boolean isProgressInactive(int position) {
        return position < list.size();
    }

    private Map.Entry<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> filterMap
            (BindItem item) {
        for (Map.Entry<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> entry :
                mapAdapter.entrySet()) {
            if (isCorrectBindAdapter(entry.getValue(), item)) {
                return entry;
            }
        }
        throw filterError(item);
    }

    private boolean isCorrectBindAdapter(BindAdapter bindAdapter, BindItem bindItem) {
        boolean isCorrectModel = bindAdapter.getModelClass().isInstance(bindItem);
        boolean isCorrectFilter = bindAdapter.filter(bindItem);
        return isCorrectModel && isCorrectFilter;
    }

    private RuntimeException filterError(BindItem item) {
        String messageError = "No BindAdapter added";
        if (!mapAdapter.isEmpty()) {
            messageError = "No BindAdapter added for item => "
                    + "[" + item.getClass().getSimpleName() + "]";
        }
        return new RuntimeException(messageError);
    }

}
