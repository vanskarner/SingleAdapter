package com.vanskarner.adapters.ui.grid_pagination;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.listener.Paginationv2;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class GridPaginationActivity extends BaseActivity implements GridPaginationContract.view,
        Paginationv2.OnLoadMoreListener {

    RecyclerView recyclerView;
    List<PersonModel> list = new ArrayList<>();
    GridPaginationAdapter adapter = new GridPaginationAdapter(list);
    Paginationv2 pagination = new Paginationv2(this,
            Paginationv2.LAST_POSITION_COMPLETE);
    GridPaginationContract.presenter presenter;

    @Override
    protected void injectPresenter() {
        presenter = new GridPaginationPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_pagination_activity);
        setupView();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerPersons);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(pagination);
        adapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = list.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pagination.pageNumber == 1) {
            findViewById(R.id.progressBarPagination).setVisibility(View.VISIBLE);
            pagination.onLoadMore();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    //Contract Methods

    @Override
    public void hideProgress() {
        findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
        adapter.hideProgress();
    }

    @Override
    public void addList(List<PersonModel> list) {
        pagination.isLoading = false;
        adapter.addList(list);
    }

    @Override
    public void showNoPages() {
        Snackbar.make(findViewById(R.id.contentPagination),
                getString(R.string.exception_no_items), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onLoadMore(int page) {
        if (page != 1) {
            adapter.showProgress();
        }
        presenter.loadMore(page);
    }

}
