package com.vanskarner.adapters.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.MovieModel;
import com.vanskarner.adapters.R;
import com.vanskarner.adapters.adapters.MoviesNew;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    MoviesNew moviesAdapter;
    ArrayList<MovieModel> rowsArrayList = new ArrayList<>();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Buscar");
//        populateData();
        initAdapter();
//        initScrollListener();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Filter myFilterB = moviesAdapter.getFilter();
                myFilterB.filter(newText);
                return false;
            }
        });
    }

    private void populateData() {
        rowsArrayList.add(new MovieModel(0, "Pedro", "IMAGE 1"));
        rowsArrayList.add(new MovieModel(1, "Juan", "IMAGE 2"));
        rowsArrayList.add(new MovieModel(2, "Diego", "IMAGE 3"));
        rowsArrayList.add(new MovieModel(3, "Ramirez", "IMAGE 4"));
        rowsArrayList.add(new MovieModel(4, "Pablo", "IMAGE 5"));
        rowsArrayList.add(new MovieModel(5, "Luis", "IMAGE 6"));
        rowsArrayList.add(new MovieModel(6, "Daniel", "IMAGE 7"));
        rowsArrayList.add(new MovieModel(7, "Fabian", "IMAGE 8"));
        rowsArrayList.add(new MovieModel(8, "Carlos", "IMAGE 9"));
        rowsArrayList.add(new MovieModel(9, "Echina", "IMAGE 10"));
    }

    private List<MovieModel> populateDataExtra() {
        List<MovieModel> movieModels = new ArrayList<>();
        movieModels.add(new MovieModel(10, "Tafur", "IMG 11"));
//        movieModels.add(null);
        movieModels.add(new MovieModel(11, "Valeria", "IMG 12"));
//        movieModels.add(new MovieModel(12, "Vanesa", "IMG 13"));
//        movieModels.add(new MovieModel(13, "Start", "IMG 14"));
//        movieModels.add(new MovieModel(14, "Sara", "IMG 15"));
        return movieModels;
    }

    private void initAdapter() {
        moviesAdapter = new MoviesNew(rowsArrayList);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            Toast.makeText(MainActivity.this, "You Clicked: " +
                            rowsArrayList.get(viewHolder.getAdapterPosition()).getTitle(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    //findLastVisibleItemPosition
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size() - 1) {

                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }

    private void loadMore() {
        moviesAdapter.showProgress();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            moviesAdapter.hideProgress();
            moviesAdapter.addList(populateDataExtra());
            isLoading = false;
        }, 2000);


    }

    public void actionUpdate(View view) {
        moviesAdapter.updateList(populateDataExtra());
    }//OK

    public void actionAdd(View view) {
        moviesAdapter.addList(populateDataExtra());
    }//OK

    public void actionShow(View view) {
        moviesAdapter.showProgress();
    }

    public void actionHide(View view) {
        moviesAdapter.hideProgress();
    }

}