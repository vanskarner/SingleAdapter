package com.vanskarner.adapters.examples.simple;

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

class MultiOneAdapter implements BindAdapter<WomanModel, MultiOneAdapter.MultiOneVH> {

    @Override
    public MultiOneVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new MultiOneVH(view);
    }

    @Override
    public void onBindViewHolder(MultiOneVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public boolean filter(WomanModel item) {
        return item.getType() == WomanModel.Type.ONE;
    }

    @Override
    public Class<WomanModel> getClassItem() {
        return WomanModel.class;
    }

    static class MultiOneVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        MultiOneVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
