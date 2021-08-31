package com.vanskarner.adapters.ui.simple_pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adapters.BasicEndlessAdapter;
import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

class SimplePaginationAdapter
        extends BasicEndlessAdapter<PersonModel, SimplePaginationAdapter.ItemClickViewHolder> {

    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SimplePaginationAdapter(List<PersonModel> list) {
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
    protected void bindItem(ItemClickViewHolder holder, PersonModel item, int position) {
        holder.itemName.setText(item.getName());
        Glide.with(holder.shapeableImageView).load(item.getImage()).into(holder.shapeableImageView);
    }

    protected static class ItemClickViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ShapeableImageView shapeableImageView;

        protected ItemClickViewHolder(@NonNull View itemView,
                                      View.OnClickListener onItemClickListener) {
            super(itemView);
            setupView(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }


        protected void setupView(View itemView) {
            itemName = itemView.findViewById(R.id.namePerson);
            shapeableImageView = itemView.findViewById(R.id.imagePerson);
        }
    }

}

