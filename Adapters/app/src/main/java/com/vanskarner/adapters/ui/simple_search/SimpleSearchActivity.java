package com.vanskarner.adapters.ui.simple_search;

import android.widget.Filter;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.BaseActivity;
import com.vanskarner.adapters.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchActivity extends BaseActivity implements SimpleSearchContract.view {

    SearchView searchView;
    RecyclerView recyclerView;
    SimpleSearchAdapter simpleSearchAdapter;
    List<MovieModel> movieModels = new ArrayList<>();
    SimpleSearchContract.presenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.simple_search_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchViewMovies);
        simpleSearchAdapter = new SimpleSearchAdapter(movieModels);
        recyclerView.setAdapter(simpleSearchAdapter);
        simpleSearchAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            MovieModel model = movieModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Filter filter = simpleSearchAdapter.getFilter();
                filter.filter(s);
                return false;
            }
        });
        //presenter initialization
        presenter = new SimpleSearchPresenter(this);
        presenter.loadList();
    }

    //Contract Methods

    @Override
    public void loadList(List<MovieModel> list) {
        simpleSearchAdapter.addList(list);
    }

}
