package com.vanskarner.adapters.ui.multi_adapters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.AdapterItem;
import com.vanskarner.adapters.common.adaptersothers.CompositeAdapter;
import com.vanskarner.adapters.ui.BaseActivity;
import com.vanskarner.adapters.ui.multi_adapters.news.MyAdapters;

import java.util.List;

public class MultiPaginationActivity extends BaseActivity
        implements MultiPaginationContract.view {

    RecyclerView recyclerView;
    CompositeAdapter adapter = new CompositeAdapter();
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
        adapter.addBindAdapter(new MyAdapters.AdapterOne());
        adapter.addBindAdapter(new MyAdapters.AdapterSecond());
        adapter.addBindAdapter(new MyAdapters.AdapterThird());
        recyclerView.setAdapter(adapter);
        presenter.loadMore();
    }

    @Override
    public void addList(List<AdapterItem> list) {
        adapter.setList(list);
    }

}
