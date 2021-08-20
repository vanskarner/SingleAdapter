package com.vanskarner.adapters.ui.simple_pagination.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.BasicClickViewHolder;
import com.vanskarner.adapters.common.adapters.BasicEndlessAdapter;
import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

public class MovieSimpleAdapter
        extends BasicEndlessAdapter<MovieModel, MovieSimpleAdapter.ItemClickViewHolder> {


    public MovieSimpleAdapter(List<MovieModel> list) {
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
    protected ItemClickViewHolder createViewHolder(View view) {
        return new ItemClickViewHolder(view, super.onItemClickListener);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, MovieModel item, int position) {
        holder.itemName.setText(item.getName());
    }

    protected static class ItemClickViewHolder extends BasicClickViewHolder {
        private TextView itemName;

        protected ItemClickViewHolder(@NonNull View itemView,
                                      View.OnClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }

        @Override
        protected void setupView(View itemView) {
            itemName = itemView.findViewById(R.id.tvItem);
        }
    }

}

