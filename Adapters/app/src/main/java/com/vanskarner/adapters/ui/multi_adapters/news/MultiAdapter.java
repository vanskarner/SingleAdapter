package com.vanskarner.adapters.ui.multi_adapters.news;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.databinding.MultiViewOneBinding;
import com.vanskarner.adapters.databinding.MultiViewSecondBinding;
import com.vanskarner.adapters.databinding.MultiViewThirdBinding;

import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MultiAdapter extends RecyclerView.Adapter<BasicVH> {

    private final List<Person> list;
    private final Visitor visitor = new ViewHolderVisitor();

    public MultiAdapter(List<Person> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public BasicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return visitor.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicVH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).accept(visitor);
    }

    public void addList(@NonNull List<Person> listAdd) {
        if (listAdd.size() > 0) {
            int currentListSize = list.size();
            list.addAll(listAdd);
            notifyItemRangeInserted(currentListSize, listAdd.size());
        }
    }

    static class OneVH extends BasicVH<Person.PersonOne> {
        MultiViewOneBinding binding;

        public OneVH(@NonNull MultiViewOneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Person.PersonOne model) {
            binding.setPersonOne(model);
        }
    }

    static class SecondVH extends BasicVH<Person.PersonSecond> {
        MultiViewSecondBinding binding;

        public SecondVH(@NonNull MultiViewSecondBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Person.PersonSecond model) {
            binding.setPersonSecond(model);
        }
    }

    static class ThirdVH extends BasicVH<Person.PersonThird> {
        MultiViewThirdBinding binding;

        public ThirdVH(@NonNull MultiViewThirdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Person.PersonThird model) {
            binding.setPersonThird(model);
        }

    }

}
