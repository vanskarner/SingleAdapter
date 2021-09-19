package com.vanskarner.adapters.ui.simple_pagination;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.listener.Pagination;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class SimplePaginationActivity extends BaseActivity
        implements SimplePaginationContract.view, Pagination.OnLoadMoreListener {

    RecyclerView recyclerView;
    SimplePaginationAdapter adapter;
    List<PersonModel> list = new ArrayList<>();
    Pagination paginationListener = Pagination
            .createWithLinear(this, Pagination.LAST_POSITION_COMPLETE);
    SimplePaginationContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.simple_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerPersons);
        adapter = new SimplePaginationAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(paginationListener);
        adapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = list.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        //presenter initialization
        presenter = new SimplePaginationPresenter(this);
        presenter.loadMore(paginationListener.pageNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //Contract Methods

    @Override
    public void hideProgress() {
        adapter.hideProgress();
    }

    private void initializeView() {
        if (paginationListener.pageNumber == 1) {
            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
        }
    }

    @Override
    public void addList(List<PersonModel> list) {
        initializeView();
        paginationListener.isLoading = false;
        adapter.addList(list);
    }

    @Override
    public void showNoPages() {
        Snackbar.make(findViewById(R.id.contentPagination), getString(R.string.exception_no_items), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void loadMore() {
        adapter.showProgress();
        presenter.loadMore(paginationListener.pageNumber);
    }
}
