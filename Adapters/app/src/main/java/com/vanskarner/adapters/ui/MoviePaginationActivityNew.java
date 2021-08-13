package com.vanskarner.adapters.ui;

import android.os.Handler;
import android.widget.Filter;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.adapters.MoviesNew;
import com.vanskarner.adapters.ui.bases.SearchPaginationActivityNew;

import java.util.ArrayList;
import java.util.List;

public class MoviePaginationActivityNew extends SearchPaginationActivityNew {

    RecyclerView recyclerView;
    SearchView searchView;
    MoviesNew moviesAdapter;
    List<MovieModel> movieModels = new ArrayList<>();

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
        moviesAdapter.addList(initialDate());
        moviesAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            MovieModel model = movieModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        searchView.setQueryHint(getString(R.string.search));
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
        moviesAdapter.showProgress();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            moviesAdapter.hideProgress();
            moviesAdapter.addList(sequentialData());
            super.isLoading = false;
        }, 2000);
    }


    @Override
    protected SearchView setSearchView() {
        return searchView;
    }

    @Override
    protected Filter setFilter() {
        return moviesAdapter.getFilter();
    }

    //Generate data

    private List<MovieModel> initialDate() {
        List<MovieModel> movieModels = new ArrayList<>();
        movieModels.add(new MovieModel(0, "Pedro", "IMAGE 1"));
        movieModels.add(new MovieModel(1, "Juan", "IMAGE 2"));
        movieModels.add(new MovieModel(2, "Diego", "IMAGE 3"));
        movieModels.add(new MovieModel(3, "Ramirez", "IMAGE 4"));
        movieModels.add(new MovieModel(4, "Pablo", "IMAGE 5"));
        movieModels.add(new MovieModel(5, "Luis", "IMAGE 6"));//-
        movieModels.add(new MovieModel(6, "Daniel", "IMAGE 7"));
        movieModels.add(new MovieModel(7, "Fabian", "IMAGE 8"));
        movieModels.add(new MovieModel(8, "Carlos", "IMAGE 9"));//-
        movieModels.add(new MovieModel(9, "Ana", "IMAGE 10"));
        return movieModels;
    }

    private List<MovieModel> sequentialData() {
        List<MovieModel> movieModels = new ArrayList<>();
        for (int i = this.movieModels.size(); i < this.movieModels.size() + 10; i++) {
            movieModels.add(new MovieModel(i, "Movie " + i, "IMG" + i));
        }
        return movieModels;
    }

}