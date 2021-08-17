package com.vanskarner.adapters.ui;

import android.widget.Filter;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.adapters.MoviesNew;
import com.vanskarner.adapters.ui.bases.SearchPaginationActivity;

import java.util.ArrayList;
import java.util.List;

public class MoviePaginationActivity extends SearchPaginationActivity
        implements MoviePaginationContract.view {

    RecyclerView recyclerView;
    SearchView searchView;
    MoviesNew moviesAdapter;
    List<MovieModel> movieModels = new ArrayList<>();
    MoviePaginationContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_adapter;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchView);
        moviesAdapter = new MoviesNew(movieModels);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            MovieModel model = movieModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        searchView.setQueryHint(getString(R.string.search));

        searchView.setOnSearchClickListener(view -> moviesAdapter.hideProgress());

        presenter = new MoviePaginationPresenter(this);
        presenter.loadMore(super.pageNumber, true);

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
        presenter.loadMore(super.pageNumber, false);
    }

    @Override
    protected SearchView setSearchView() {
        return searchView;
    }

    @Override
    protected Filter setFilter() {
        return moviesAdapter.getFilter();
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
    public void addList(List<MovieModel> list) {
        super.isLoading = false;
        if (searchView.isIconified()) {
            //unused SearchView
            moviesAdapter.addList(list);
        } else {
            //SearchView in use
            super.pageNumber--;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}