package com.utility.adapters.examples.simple;

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

class LinearAdapter implements BindAdapter<WomanModel, LinearAdapter.LinearVH> {

    @Override
    public LinearVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new LinearVH(view);
    }

    @Override
    public void onBindViewHolder(LinearVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class LinearVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        LinearVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
