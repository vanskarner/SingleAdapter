package com.vanskarner.adapters.ui.simple_search;

import android.view.View;
import android.widget.TextView;

import com.vanskarner.adapters.R;

import androidx.annotation.NonNull;

import com.vanskarner.adapters.common.adapters.BasicClickViewHolder;
import com.vanskarner.adapters.common.adapters.CustomFilterAdapter;
import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

class MovieAdapter extends CustomFilterAdapter<MovieModel, MovieAdapter.ItemViewHolder> {

    public MovieAdapter(List<MovieModel> list) {
        super(list);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_movie;
    }

    @Override
    protected ItemViewHolder createViewHolder(View view) {
        return new ItemViewHolder(view, super.onItemClickListener);
    }

    @Override
    protected void bindItem(ItemViewHolder holder, MovieModel item, int position) {
        holder.tvName.setText(item.getName());
    }

    @Override
    public boolean filterCondition(MovieModel item, String filterPattern) {
        return item.getName().toLowerCase().contains(filterPattern);
    }

    public static class ItemViewHolder extends BasicClickViewHolder {
        private TextView tvName;

        protected ItemViewHolder(@NonNull View itemView,
                                 View.OnClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }

        @Override
        protected void setupView(View itemView) {
            tvName = itemView.findViewById(R.id.tvItem);
        }
    }
}
