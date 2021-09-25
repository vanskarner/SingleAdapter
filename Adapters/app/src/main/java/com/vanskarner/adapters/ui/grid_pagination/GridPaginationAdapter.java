package com.vanskarner.adapters.ui.grid_pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapterv2.EndlessRecyclerAdapter;
import com.vanskarner.adapters.databinding.ItemGridBinding;
import com.vanskarner.adapters.models.PersonModel;

import java.util.List;
import java.util.Objects;

class GridPaginationAdapter
        extends EndlessRecyclerAdapter<PersonModel, GridPaginationAdapter.ItemClickViewHolder> {

    private View.OnClickListener onItemClickListener;

    public GridPaginationAdapter(@NonNull List<PersonModel> list) {
        super(list);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int setLoadLayout() {
        return R.layout.item_loading;
    }

    @Override
    protected ItemClickViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent) {
        ItemGridBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.item_grid, parent, false);
        return new ItemClickViewHolder(binding, onItemClickListener);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, PersonModel item, int position) {
        holder.binding.setPerson(item);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        Objects.requireNonNull(gridLayoutManager)
                .setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int currentViewType = getItemViewType(position);
                        return (currentViewType == PROGRESS_VIEW) ? 2 : 1;
                    }
                });
    }


    protected static class ItemClickViewHolder extends RecyclerView.ViewHolder {
        private final ItemGridBinding binding;

        protected ItemClickViewHolder(ItemGridBinding binding,
                                      View.OnClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onItemClickListener);
        }

    }

}

