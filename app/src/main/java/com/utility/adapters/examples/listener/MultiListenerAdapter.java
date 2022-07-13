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

    private final OnClickMultiListener listener;

    public MultiListenerAdapter(OnClickMultiListener listener) {
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
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class ListenerVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        ListenerVH(@NonNull View itemView, OnClickMultiListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(view -> listener.onClickItem(getAdapterPosition()));
            image.setOnClickListener(view -> listener.onClickImageItem(getAdapterPosition()));
            name.setOnClickListener(view -> listener.onClickNameItem(getAdapterPosition()));
        }
    }

    public interface OnClickMultiListener {
        void onClickItem(int position);

        void onClickImageItem(int position);

        void onClickNameItem(int position);
    }

}
