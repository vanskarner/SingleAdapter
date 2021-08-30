package com.vanskarner.adapters.ui.simple_pagination;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.PaginationActivity;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class SimplePaginationActivity extends PaginationActivity
        implements SimplePaginationContract.view {

    RecyclerView recyclerView;
    SimplePaginationAdapter simplePaginationAdapter;
    List<PersonModel> personModels = new ArrayList<>();
    SimplePaginationContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.simple_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        simplePaginationAdapter = new SimplePaginationAdapter(personModels);
        recyclerView.setAdapter(simplePaginationAdapter);
        simplePaginationAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = personModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        //presenter initialization
        presenter = new SimplePaginationPresenter(this);
        presenter.loadMore(super.pageNumber);
    }

    @Override
    protected RecyclerView setRecyclerView() {
        return recyclerView;
    }

    @Override
    protected int setLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
        return manager.findLastCompletelyVisibleItemPosition();
    }

    @Override
    protected void loadMore() {
        presenter.loadMore(super.pageNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //Contract Methods

    @Override
    public void showProgress() {
        simplePaginationAdapter.showProgress();
    }

    @Override
    public void hideProgress() {
        simplePaginationAdapter.hideProgress();
    }

    @Override
    public void initializeView() {
        recyclerView.setVisibility(View.VISIBLE);
        findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
    }

    @Override
    public void addList(List<PersonModel> list) {
        super.isLoading = false;
        super.pageNumber++;
        simplePaginationAdapter.addList(list);
    }

    @Override
    public void showNoPages() {
        Snackbar
                .make(findViewById(R.id.contentPagination),
                        getString(R.string.exception_no_items),
                        Snackbar.LENGTH_SHORT)
                .show();
    }
}
