package com.vanskarner.adapters.ui.search_pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.vanskarner.adapters.common.adapterv2.EndlessFilterRecyclerAdapter;
import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.R;

import java.util.List;

class SearchPaginationFilterAdapter
        extends EndlessFilterRecyclerAdapter<PersonModel, SearchPaginationFilterAdapter.ItemClickViewHolder> {

    private View.OnClickListener onItemClickListener;

    public SearchPaginationFilterAdapter(List<PersonModel> list) {
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
        View view = inflater.inflate(R.layout.item_simple, parent, false);
        return new ItemClickViewHolder(view, onItemClickListener);
    }

    @Override
    public boolean onFilterCondition(PersonModel item, String filterPattern) {
        return item.getName().toLowerCase().contains(filterPattern);
    }

    @Override
    protected void bindItem(ItemClickViewHolder holder, PersonModel item, int position) {
        holder.itemName.setText(item.getName());
        Glide.with(holder.itemImage).load(item.getImage()).into(holder.itemImage);
    }

    protected static class ItemClickViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ShapeableImageView itemImage;

        protected ItemClickViewHolder(@NonNull View itemView,
                                      View.OnClickListener onItemClickListener) {
            super(itemView);
            setupView(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }


        protected void setupView(View itemView) {
            itemName = itemView.findViewById(R.id.namePerson);
            itemImage = itemView.findViewById(R.id.imagePerson);
        }

    }

}
