package com.vanskarner.adapters.ui.simple_pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.BasicEndlessAdapter;
import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

class SimplePaginationAdapter
        extends BasicEndlessAdapter<MovieModel, SimplePaginationAdapter.ItemClickViewHolder> {

    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SimplePaginationAdapter(List<MovieModel> list) {
        super(list);
    }

    @Override
    protected int setLoadLayout() {
        return R.layout.item_loading;
    }

    @Override
    protected ItemClickViewHolder setViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new ItemClickViewHolder(view, onItemClickListener);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, MovieModel item, int position) {
        holder.itemName.setText(item.getName());
    }

    protected static class ItemClickViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;

        protected ItemClickViewHolder(@NonNull View itemView,
                                      View.OnClickListener onItemClickListener) {
            super(itemView);
            setupView(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }


        protected void setupView(View itemView) {
            itemName = itemView.findViewById(R.id.tvItem);
        }
    }

}

