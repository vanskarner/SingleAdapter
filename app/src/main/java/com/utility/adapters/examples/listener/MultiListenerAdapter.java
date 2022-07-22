package com.utility.adapters.examples.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.utility.adapters.R;

import com.utility.adapters.examples.WomanModel;
import com.vanskarner.singleadapter.BindAdapter;

class MultiListenerAdapter implements BindAdapter<WomanModel, MultiListenerAdapter.ListenerVH> {

    private View.OnClickListener itemListener;
    private View.OnClickListener itemImageListener;
    private View.OnClickListener itemNameListener;

    public void setListeners(View.OnClickListener itemListener,
                             View.OnClickListener itemImageListener,
                             View.OnClickListener itemNameListener) {
        this.itemListener = itemListener;
        this.itemImageListener = itemImageListener;
        this.itemNameListener = itemNameListener;
    }

    @Override
    public ListenerVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new ListenerVH(view, itemListener, itemImageListener, itemNameListener);
    }

    @Override
    public void onBindViewHolder(ListenerVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class ListenerVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ListenerVH(@NonNull View itemView, View.OnClickListener listenerItem,
                   View.OnClickListener listenerImageItem,
                   View.OnClickListener listenerNameItem) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            super.itemView.setTag(this);
            image.setTag(this);
            name.setTag(this);
            super.itemView.setOnClickListener(listenerItem);
            image.setOnClickListener(listenerImageItem);
            name.setOnClickListener(listenerNameItem);
        }
    }

}
