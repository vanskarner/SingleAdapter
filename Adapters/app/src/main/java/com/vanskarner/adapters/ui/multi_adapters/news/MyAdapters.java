package com.vanskarner.adapters.ui.multi_adapters.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindAdapter;
import com.vanskarner.adapters.databinding.MultiViewOneBinding;
import com.vanskarner.adapters.databinding.MultiViewSecondBinding;
import com.vanskarner.adapters.databinding.MultiViewThirdBinding;

public class MyAdapters {

    public static class AdapterOne
            extends BindAdapter<Person.PersonOne, AdapterOne.OneViewHolder> {
        @Override
        public OneViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                LayoutInflater inflater) {
            return new OneViewHolder(MultiViewOneBinding
                    .inflate(inflater, parent, false));
        }

        @Override
        public void onBindViewHolder(OneViewHolder viewHolder, Person.PersonOne item) {
            viewHolder.binding.setPersonOne(item);
        }

        @Override
        public int getLayoutId() {
            return R.layout.multi_view_one;
        }

        @Override
        public Class<Person.PersonOne> getModelClass() {
            return Person.PersonOne.class;
        }

        static class OneViewHolder extends RecyclerView.ViewHolder {
            protected MultiViewOneBinding binding;

            public OneViewHolder(@NonNull MultiViewOneBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

    public static class AdapterSecond
            extends BindAdapter<Person.PersonSecond, AdapterSecond.SecondViewHolder> {
        @Override
        public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   LayoutInflater inflater) {
            return new SecondViewHolder(MultiViewSecondBinding
                    .inflate(inflater, parent, false));
        }

        @Override
        public void onBindViewHolder(SecondViewHolder viewHolder, Person.PersonSecond item) {
            viewHolder.binding.setPersonSecond(item);
        }

        @Override
        public int getLayoutId() {
            return R.layout.multi_view_second;
        }

        @Override
        public Class<Person.PersonSecond> getModelClass() {
            return Person.PersonSecond.class;
        }

        static class SecondViewHolder extends RecyclerView.ViewHolder {
            protected MultiViewSecondBinding binding;

            public SecondViewHolder(@NonNull MultiViewSecondBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

    public static class AdapterThird
            extends BindAdapter<Person.PersonThird, AdapterThird.ThirdViewHolder> {
        @Override
        public ThirdViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                  LayoutInflater inflater) {
            return new ThirdViewHolder(MultiViewThirdBinding
                    .inflate(inflater, parent, false));
        }

        @Override
        public void onBindViewHolder(ThirdViewHolder viewHolder, Person.PersonThird item) {
            viewHolder.binding.setPersonThird(item);
        }

        @Override
        public int getLayoutId() {
            return R.layout.multi_view_third;
        }

        @Override
        public Class<Person.PersonThird> getModelClass() {
            return Person.PersonThird.class;
        }

        static class ThirdViewHolder extends RecyclerView.ViewHolder {
            protected MultiViewThirdBinding binding;

            public ThirdViewHolder(@NonNull MultiViewThirdBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}
