package com.vanskarner.adapters.ui.grid_pagination;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.listener.PaginationListener;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class GridPaginationActivity extends BaseActivity implements GridPaginationContract.view {

    RecyclerView recyclerView;
    GridPaginationAdapter adapter;
    List<PersonModel> list = new ArrayList<>();
    PaginationListener paginationListener;
    GridPaginationContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.grid_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerPersons);
        adapter = new GridPaginationAdapter(list);
        recyclerView.setAdapter(adapter);
        paginationListener = new PaginationListener() {
            @Override
            protected void loadMore() {
                adapter.showProgress();
                presenter.loadMore(paginationListener.pageNumber);
            }
        };
        recyclerView.addOnScrollListener(paginationListener);
        adapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = list.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });

        //presenter initialization
        presenter = new GridPaginationPresenter(this);
        presenter.loadMore(paginationListener.pageNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initializeView() {
        if (paginationListener.pageNumber == 1) {
            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
        }
    }

    //Contract Methods

    @Override
    public void hideProgress() {
        adapter.hideProgress();
    }

    @Override
    public void addList(List<PersonModel> list) {
        initializeView();
        paginationListener.isLoading = false;
        adapter.addList(list);
    }

    @Override
    public void showNoPages() {
        Snackbar.make(findViewById(R.id.contentPagination),
                getString(R.string.exception_no_items), Snackbar.LENGTH_SHORT)
                .show();
    }
}
