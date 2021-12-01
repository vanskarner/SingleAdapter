package com.vanskarner.adapters.ui.multi_adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.ui.BaseActivity;
import com.vanskarner.adapters.ui.multi_adapters.news.MultiAdapter;
import com.vanskarner.adapters.ui.multi_adapters.news.Person;

import java.util.ArrayList;
import java.util.List;

public class MultiPaginationActivity extends BaseActivity
        implements MultiPaginationContract.view{

    RecyclerView recyclerView;
    List<Person> list = new ArrayList<>();
    MultiAdapter adapter = new MultiAdapter(list);
    MultiPaginationContract.presenter presenter;

    @Override
    protected void injectPresenter() {
        presenter = new MultiPaginationPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_pagination_activity);
        setupView();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerPersons);
        recyclerView.setAdapter(adapter);
        presenter.loadMore();
    }

    @Override
    public void addList(List<Person> list) {
        adapter.addList(list);
    }

}
