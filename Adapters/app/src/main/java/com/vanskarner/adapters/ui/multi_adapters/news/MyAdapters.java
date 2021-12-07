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
            implements BindAdapter<AdapterOne.OneViewHolder,Person.PersonOne> {
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
        public int setLayoutId() {
            return R.layout.multi_view_one;
        }

        @Override
        public Class<Person.PersonOne> setModelClass() {
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
            implements BindAdapter<AdapterSecond.SecondViewHolder,Person.PersonSecond> {
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
        public int setLayoutId() {
            return R.layout.multi_view_second;
        }

        @Override
        public Class<Person.PersonSecond> setModelClass() {
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
            implements BindAdapter<AdapterThird.ThirdViewHolder,Person.PersonThird> {
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
        public int setLayoutId() {
            return R.layout.multi_view_third;
        }

        @Override
        public Class<Person.PersonThird> setModelClass() {
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
