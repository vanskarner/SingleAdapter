package com.vanskarner.adapters.ui.search_pagination_custom.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vanskarner.adapters.models.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.BasicClickViewHolder;
import com.vanskarner.adapters.common.adapters.CustomFilterAdapter;

import java.util.List;

public class MoviesFilter
        extends CustomFilterAdapter<MovieModel, MoviesFilter.ItemClickViewHolder> {

    public MoviesFilter(List<MovieModel> list) {
        super(list);
    }

    @Override
    public boolean filterCondition(MovieModel item, String filterPatter) {
        return item.getName().toLowerCase().equals(filterPatter);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_movie;
    }

    @Override
    protected ItemClickViewHolder createViewHolder(View view) {
        return new ItemClickViewHolder(view, super.onItemClickListener);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, MovieModel item, int position) {
        holder.itemTitle.setText(item.getName());
    }

    protected static class ItemClickViewHolder extends BasicClickViewHolder {
        private TextView itemTitle;

        protected ItemClickViewHolder(@NonNull View itemView,
                                      View.OnClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }

        @Override
        protected void setupView(View itemView) {
            itemTitle = itemView.findViewById(R.id.tvItem);
        }
    }

}

