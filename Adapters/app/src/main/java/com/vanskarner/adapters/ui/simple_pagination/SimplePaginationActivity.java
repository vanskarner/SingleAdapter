package com.vanskarner.adapters.ui.simple_pagination;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.PaginationActivity;
import com.vanskarner.adapters.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class SimplePaginationActivity extends PaginationActivity
        implements SimplePaginationContract.view {

    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;
    List<MovieModel> movieModels = new ArrayList<>();
    SimplePaginationContract.presenter presenter;

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
    protected int setLayout() {
        return R.layout.simple_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        moviesAdapter = new MoviesAdapter(movieModels);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            MovieModel model = movieModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        //presenter initialization
        presenter = new SimplePaginationPresenter(this);
        presenter.loadMore(super.pageNumber);
    }

    @Override
    public void showProgress() {
        moviesAdapter.showProgress();
    }

    @Override
    public void hideProgress() {
        moviesAdapter.hideProgress();
    }

    @Override
    public void showNecessaryViews() {
        recyclerView.setVisibility(View.VISIBLE);
        findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
    }

    @Override
    public void addList(List<MovieModel> list) {
        super.isLoading = false;
        super.pageNumber++;
        moviesAdapter.addList(list);
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
