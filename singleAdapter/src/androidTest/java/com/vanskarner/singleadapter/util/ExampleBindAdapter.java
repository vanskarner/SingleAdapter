package com.vanskarner.singleadapter.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.singleadapter.BindAdapter;
import com.vanskarner.singleadapter.R;

public class ExampleBindAdapter implements BindAdapter<ExampleModel, ExampleBindAdapter.ExampleVH> {

    @Override
    public ExampleVH onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.example_item, parent, false);
        return new ExampleVH(view);
    }

    @Override
    public void onBindViewHolder(ExampleVH viewHolder, ExampleModel item) {
        viewHolder.name.setText(item.getName());
    }

    @Override
    public Class<ExampleModel> getClassItem() {
        return ExampleModel.class;
    }

    static class ExampleVH extends RecyclerView.ViewHolder {
        TextView name;

        ExampleVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

}