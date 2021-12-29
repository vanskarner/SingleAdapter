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
    private BaseDiffCallback<? extends BindItem> defaultDiff;
    private final AsyncListDiffer<BindItem> listDiffer;
    private final EndlessLoad endlessLoad;

    public SingleAdapter() {
        mapAdapter = new HashMap<>();
        defaultDiff = new DefaultBaseDiff();
        listDiffer = new AsyncListDiffer<>(this,
                (DiffUtil.ItemCallback<BindItem>) defaultDiff);
        endlessLoad = new EndlessLoad(listDiffer);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return endlessLoad.getAdapter().getLayoutId() == viewType ?
                endlessLoad.getAdapter().onCreateViewHolder(parent, inflater) :
                Objects.requireNonNull(mapAdapter.get(viewType))
                        .onCreateViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!isLoad(position)) {
            filterMap(position).getValue().onBindViewHolder(holder, getItem(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isLoad(position) ?
                endlessLoad.getAdapter().getLayoutId() :
                filterMap(position).getKey();
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public boolean isLoad(int position) {
        BindItem item = getItem(position);
        return endlessLoad.isLoadInstance(item);
    }

    public void add(@NonNull final BindAdapter bindAdapter) {
        int classHashCode = bindAdapter.getClass().hashCode();
        mapAdapter.put(classHashCode, bindAdapter);
    }

    public void set(@NonNull final List<? extends BindItem> items) {
        endlessLoad.setProgressFalse();
        listDiffer.submitList(new ArrayList<>(items));
    }

    public void set(@NonNull final BaseDiffCallback<? extends BindItem> diffCallback) {
        this.defaultDiff = diffCallback;
    }

    public void set(final int loadLayoutId) {
        endlessLoad.getAdapter().setLayoutId(loadLayoutId);
    }

    public void showProgress() {
        endlessLoad.showProgress();
    }

    public void hideProgress() {
        endlessLoad.hideProgress();
    }

    private List<BindItem> getList() {
        return listDiffer.getCurrentList();
    }

    private BindItem getItem(int position) {
        return getList().get(position);
    }

    private Map.Entry<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> filterMap
            (int position) {
        BindItem item = getItem(position);
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
