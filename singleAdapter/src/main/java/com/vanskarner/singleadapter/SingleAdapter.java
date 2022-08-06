package com.vanskarner.singleadapter;

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
            filterMap(position).getValue().onBindViewHolder(holder, getBindItem(position));
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

    /**
     * Check if the item in the current position is the progress view
     *
     * @param position current item position
     * @return true if the item at the specified position belongs to the loading view
     */
    public boolean isLoad(int position) {
        BindItem item = getBindItem(position);
        return endlessLoad.isLoadInstance(item);
    }

    /**
     * Add BindAdapters the final view will contain
     *
     * @param bindAdapter an implementation of BindAdapter
     */
    public void add(@NonNull final BindAdapter bindAdapter) {
        int classHashCode = bindAdapter.getClass().hashCode();
        mapAdapter.put(classHashCode, bindAdapter);
    }

    /**
     * Sets the list of items in the adapter and hides the progress view if it's visible
     *
     * @param items Adapter data
     */
    public void set(@NonNull final List<? extends BindItem> items) {
        listDiffer.submitList(new ArrayList<>(items));
    }

    /**
     * Set custom BaseDiffCallback
     *
     * @param diffCallback Custom BaseDiffCallback
     */
    public void set(@NonNull final BaseDiffCallback<? extends BindItem> diffCallback) {
        this.defaultDiff = diffCallback;
    }

    /**
     * Set load layout identifier that will be displayed when using
     * {@link #showProgress()} method
     *
     * @param loadLayoutId identify layout for load visualization
     */
    public void set(final int loadLayoutId) {
        endlessLoad.getAdapter().setLayoutId(loadLayoutId);
    }

    /**
     * Display load layout that is set with {@link #set(int)} method
     * In case the layout has not been established, this method will do nothing.
     */
    public void showProgress() {
        endlessLoad.showProgress();
    }

    /**
     * Hide load layout.
     * You don't need to use it if you call the {@link #set(List)} method
     */
    public void hideProgress() {
        endlessLoad.hideProgress();
    }

    /**
     * If the argument is true, it displays the progress layout set with the {@link #set(List)}
     * method. If you have not established a design, the default design will be loaded.
     * If the argument is false, the progress will be hidden. There is no need to hide the progress
     * if you call the {@link #set(List)} method
     *
     * @param isVisible indicator to show or hide progress
     */
    public void setVisibleProgress(boolean isVisible) {
        endlessLoad.setVisibleProgress(isVisible);
    }

    /**
     * Gets the list item specifying the position
     *
     * @param position position in the list
     * @param <T>      generic that implements BindItem
     * @return instance of a class that implements BindItem
     * @throws IndexOutOfBoundsException index of some type is out of range
     */
    public <T extends BindItem> T getItem(int position) {
        return (T) getBindItem(position);
    }

    private List<BindItem> getList() {
        return listDiffer.getCurrentList();
    }

    private BindItem getBindItem(int position) {
        return getList().get(position);
    }

    private Map.Entry<Integer, BindAdapter<BindItem, RecyclerView.ViewHolder>> filterMap
            (int position) {
        BindItem item = getBindItem(position);
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