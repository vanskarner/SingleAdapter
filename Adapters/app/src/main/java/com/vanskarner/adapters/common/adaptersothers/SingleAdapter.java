package com.vanskarner.adapters.common.adaptersothers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> mapAdapter;
    private AsyncListDiffer<BindItem> listDiffer;
    private final EndlessMethods endlessMethods;

    public SingleAdapter() {
        mapAdapter = new HashMap<>();
        BaseDiffCallback<? extends BindItem> defaultDiff = new DefaultBaseDiff();
        listDiffer = new AsyncListDiffer<>(this, (DiffUtil.ItemCallback<BindItem>) defaultDiff);
        endlessMethods = new EndlessMethods(listDiffer);
    }

    public void setList(@NonNull final List<? extends BindItem> newList) {
        endlessMethods.setProgressFalse();
        listDiffer.submitList(new ArrayList<>(newList));
    }

    public void add(@NonNull final BindAdapter bindAdapter) {
        int classHashCode = bindAdapter.getClass().hashCode();
        mapAdapter.put(classHashCode, bindAdapter);
    }

    public void add(@NonNull final BaseDiffCallback<? extends BindItem> diffCallback) {
        this.listDiffer = new AsyncListDiffer<>(this, (DiffUtil.ItemCallback<BindItem>) diffCallback);
    }

    public void add(final int idLoadLayout) {
        endlessMethods.setLayoutId(idLoadLayout);
    }

    public void showProgress() {
        endlessMethods.showProgress();
    }

    public void hideProgress() {
        endlessMethods.hideProgress();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return endlessMethods.getLayoutId() == viewType ?
                endlessMethods.getAdapter().onCreateViewHolder(parent, inflater) :
                Objects.requireNonNull(mapAdapter.get(viewType))
                        .onCreateViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BindItem item = getItem(position);
        if (!isLoadInstance(item)) {
            filterMap(item).getValue().onBindViewHolder(holder, item);
        }
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        BindItem item = getItem(position);
        return isLoadInstance(item) ?
                endlessMethods.getLayoutId() :
                filterMap(item).getKey();
    }

    private boolean isLoadInstance(BindItem position) {
        return position instanceof LoadBindItem;
    }

    private List<BindItem> getList() {
        return listDiffer.getCurrentList();
    }

    private BindItem getItem(int position) {
        return getList().get(position);
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
        boolean isCorrectModel = bindAdapter.getClassItem().isInstance(bindItem);
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
