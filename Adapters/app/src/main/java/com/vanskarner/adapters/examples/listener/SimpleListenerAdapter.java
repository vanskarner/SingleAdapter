package com.vanskarner.adapters.examples.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.examples.WomanModel;

class SimpleListenerAdapter implements BindAdapter<WomanModel, SimpleListenerAdapter.ListenerVH> {

    private final OnClickItemListener listener;

    public SimpleListenerAdapter(OnClickItemListener listener) {
        this.listener = listener;
    }

    @Override
    public ListenerVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new ListenerVH(view, listener);
    }

    @Override
    public void onBindViewHolder(ListenerVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public Class<WomanModel> getModelItem() {
        return WomanModel.class;
    }

    static class ListenerVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ListenerVH(@NonNull View itemView, OnClickItemListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(view -> listener.OnClickItem(getAdapterPosition()));
        }
    }

    public interface OnClickItemListener {
        void OnClickItem(int position);
    }

}
