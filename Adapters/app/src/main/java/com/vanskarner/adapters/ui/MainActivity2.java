package com.vanskarner.adapters.ui;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.adapters.MoviesNew;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends BasePaginationActiviy {

    RecyclerView recyclerView;
    SearchView searchView;
    MoviesNew moviesAdapter;
    ArrayList<MovieModel> rowsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Buscar");
        initAdapter();
        initScrollListener();
        searchView.setOnQueryTextListener(super.searchViewOnQueryTextListener(moviesAdapter.getFilter()));
    }

    private List<MovieModel> populateData() {
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
        movieModels.add(new MovieModel(9, "Echina", "IMAGE 10"));
        return movieModels;
    }

    private List<MovieModel> populateDataExtra2() {
        List<MovieModel> movieModels = new ArrayList<>();
        for (int i = rowsArrayList.size(); i < rowsArrayList.size() + 10; i++) {
            movieModels.add(new MovieModel(i, "Add N°" + i, "IMG" + i));
        }
        return movieModels;
    }

    private void initAdapter() {
        moviesAdapter = new MoviesNew(rowsArrayList);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.addList(populateData());
        moviesAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            Toast.makeText(MainActivity2.this, "You Clicked: " +
                            rowsArrayList.get(viewHolder.getAdapterPosition()).toString(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(super.recyclerOnScrollListener());
    }

    @Override
    protected int lastVisibleItemPosition() {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        return (manager != null) ? manager.findLastCompletelyVisibleItemPosition() : 0;
    }

    @Override
    protected void loadMore() {
        moviesAdapter.showProgress();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            moviesAdapter.hideProgress();
            moviesAdapter.addList(populateDataExtra2());
            isLoading = false;
        }, 2000);
    }

    @Override
    protected int setListSize() {
        return rowsArrayList.size();
    }

}