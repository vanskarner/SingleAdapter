package com.vanskarner.adapters.examples.progress;

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

class WomanAdapter implements BindAdapter<WomanModel, WomanAdapter.WomanVH> {

    @Override
    public WomanVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.linear_item, parent, false);
        return new WomanVH(view);
    }

    @Override
    public void onBindViewHolder(WomanVH viewHolder, WomanModel item) {
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
        viewHolder.name.setText(item.getFirstName());
    }

    @Override
    public Class<WomanModel> getModelClass() {
        return WomanModel.class;
    }

    static class WomanVH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public WomanVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
