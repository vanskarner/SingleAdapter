package com.vanskarner.adapters.ui.staggered_pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.EndlessAdapter;
import com.vanskarner.adapters.databinding.ItemStaggeredBinding;
import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

class StaggeredPaginationAdapter
        extends EndlessAdapter<PersonModel, StaggeredPaginationAdapter.ItemClickViewHolder> {

    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public StaggeredPaginationAdapter(List<PersonModel> list) {
        super(list);
    }

    @Override
    protected int setLoadLayout() {
        return R.layout.item_loading;
    }

    @Override
    protected ItemClickViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent) {
        ItemStaggeredBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.item_staggered, parent, false);
        return new ItemClickViewHolder(binding, onItemClickListener);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, PersonModel item, int position) {
        holder.binding.setPerson(item);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getItemViewType() == VIEW_TYPE_LOADING) {
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }

    protected static class ItemClickViewHolder extends RecyclerView.ViewHolder {
        private final ItemStaggeredBinding binding;

        protected ItemClickViewHolder(ItemStaggeredBinding binding,
                                      View.OnClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onItemClickListener);
        }

    }

}

