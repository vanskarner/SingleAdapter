package com.vanskarner.adapters.ui.staggered_pagination;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.common.listener.PaginationListener;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class StaggeredPaginationActivity extends BaseActivity implements StaggeredPaginationContract.view {

    RecyclerView recyclerView;
    StaggeredPaginationAdapter adapter;
    List<PersonModel> list = new ArrayList<>();
    PaginationListener paginationListener;
    StaggeredPaginationContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.staggered_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerPersons);
        adapter = new StaggeredPaginationAdapter(list);
        recyclerView.setAdapter(adapter);
        paginationListener = new PaginationListener() {
            @Override
            protected void loadMore() {
                adapter.showProgress();
                presenter.loadMore(paginationListener.pageNumber);
            }

            @Override
            protected int itemPositionForLoading(RecyclerView.LayoutManager manager) {
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) manager;
                return PaginationListener.lastPositionStaggeredGrid(layoutManager);
            }
        };
        recyclerView.addOnScrollListener(paginationListener);
        adapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = list.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });

        //presenter initialization
        presenter = new StaggeredPaginationPresenter(this);
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
