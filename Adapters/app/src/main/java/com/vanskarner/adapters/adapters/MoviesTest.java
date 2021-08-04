package com.vanskarner.adapters.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.BasicEndlessAdapter;

import java.util.List;

public class MoviesTest extends BasicEndlessAdapter<MovieModel, MoviesTest.ItemViewHolder> {

    public MoviesTest(List<MovieModel> list) {
        super(list);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.item_movie;
    }

    @Override
    protected int setLoadLayout() {
        return R.layout.item_loading;
    }

    @Override
    protected ItemViewHolder createViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    protected boolean filterCondition(MovieModel item, String filterPattern) {
        return item.getTitle().toLowerCase().contains(filterPattern);
    }

    @Override
    protected void bindItem(ItemViewHolder holder, MovieModel item, int position) {
        holder.itemTitle.setText(item.getTitle());
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.tvItem);
            if (list.get(getItemCount() - 1) != null) {
                itemView.setTag(this);
                itemView.setOnClickListener(onItemClickListener);
            }
        }
    }

}

