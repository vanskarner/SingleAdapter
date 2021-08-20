package com.vanskarner.adapters.ui.simple_pagination;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.bases.PaginationActivity;
import com.vanskarner.adapters.models.MovieModel;
import com.vanskarner.adapters.ui.simple_pagination.adapters.MovieSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaginationSimpleActivity extends PaginationActivity
implements PaginationSimpleContract.view{

    RecyclerView recyclerView;
    MovieSimpleAdapter movieSimpleAdapter;
    List<MovieModel> movieModels=new ArrayList<>();

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

    }

    @Override
    protected int setLayout() {
        return R.layout.simple_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        movieSimpleAdapter=new MovieSimpleAdapter(movieModels);
        recyclerView.setAdapter(movieSimpleAdapter);
        movieSimpleAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            MovieModel model = movieModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showNecessaryViews() {

    }

    @Override
    public void addList(List<MovieModel> list) {

    }

    @Override
    public void showNoPages() {

    }
}
