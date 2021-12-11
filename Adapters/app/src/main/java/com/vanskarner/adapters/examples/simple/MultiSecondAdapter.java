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

class MultiSecondAdapter implements BindAdapter<WomanModel, MultiSecondAdapter.SecondVH> {

    @Override
    public SecondVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(setLayoutId(), parent, false);
        return new SecondVH(view);
    }

    @Override
    public void onBindViewHolder(SecondVH viewHolder, WomanModel item) {
        viewHolder.name.setText(item.getFirstName());
        Glide.with(viewHolder.image).load(item.getImageID()).into(viewHolder.image);
    }

    @Override
    public int setLayoutId() {
        return R.layout.item_grid2;
    }

    @Override
    public Class<WomanModel> setModelClass() {
        return WomanModel.class;
    }

    @Override
    public boolean filterItem(WomanModel item) {
        return item.getType() == WomanModel.Type.SECOND;
    }

    static class SecondVH extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public SecondVH(@NonNull View itemView) {
            super(itemView);
            setupView(itemView);
        }

        void setupView(View view) {
            name = view.findViewById(R.id.name);
            image = view.findViewById(R.id.image);
        }
    }

}
