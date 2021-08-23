package com.vanskarner.adapters.ui.simple_search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanskarner.adapters.R;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.common.adapters.BasicFilterAdapter;
import com.vanskarner.adapters.databinding.ItemMovieBinding;
import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

class SimpleSearchAdapter extends
        BasicFilterAdapter<MovieModel, SimpleSearchAdapter.ItemViewHolder> {

    private View.OnClickListener onItemClickListener;

    public SimpleSearchAdapter(List<MovieModel> list) {
        super(list);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected ItemViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = DataBindingUtil
                .inflate(inflater, R.layout.item_movie, parent, false);
        return new ItemViewHolder(itemMovieBinding, onItemClickListener);
    }

    @Override
    protected void bindItem(ItemViewHolder holder, MovieModel item, int position) {
        holder.itemMovieBinding.setMovie(item);
    }

    @Override
    public boolean filterCondition(MovieModel item, String filterPattern) {
        return item.getName().toLowerCase().contains(filterPattern);
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding itemMovieBinding;

        public ItemViewHolder(ItemMovieBinding itemMovieBinding,
                              View.OnClickListener onItemClickListener) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onItemClickListener);
        }

    }
}
