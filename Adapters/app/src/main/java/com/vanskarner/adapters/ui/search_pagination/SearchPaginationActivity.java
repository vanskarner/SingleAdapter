package com.vanskarner.adapters.ui.search_pagination;

import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vanskarner.adapters.common.bases.PaginationActivity;
import com.vanskarner.adapters.common.reactive_views.RxSearchObservable;
import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPaginationActivity extends PaginationActivity
        implements SearchPaginationContract.view {

    RecyclerView recyclerView;
    SearchView searchView;
    SearchPaginationAdapter searchPaginationAdapter;
    List<PersonModel> personModels = new ArrayList<>();
    SearchPaginationContract.presenter presenter;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected int setLayout() {
        return R.layout.search_pagination_activity;
    }

    @Override
    protected void setupView() {
        recyclerView = findViewById(R.id.recyclerMovies);
        searchView = findViewById(R.id.searchView);
        searchPaginationAdapter = new SearchPaginationAdapter(personModels);
        recyclerView.setAdapter(searchPaginationAdapter);
        searchPaginationAdapter.setOnItemClickListener(view -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            PersonModel model = personModels.get(viewHolder.getAdapterPosition());
            Toast.makeText(this, model.toString(), Toast.LENGTH_SHORT).show();
        });
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnSearchClickListener(view -> searchPaginationAdapter.hideProgress());
        Disposable disposable=RxSearchObservable.fromView(searchView)
                .debounce(350, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Filter filter=searchPaginationAdapter.getFilter();
                    filter.filter(s);
                });
        compositeDisposable.add(disposable);

        //presenter initialization
        presenter = new SearchPaginationPresenter(this);
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
    protected RecyclerView.OnScrollListener recyclerOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (!isLoading && searchView.isIconified() && manager != null &&
                        setLastVisibleItemPosition(manager) == manager.getItemCount() - 1) {
                    loadMore();
                    isLoading = true;
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        presenter.onDestroy();
    }

    //Contract Methods

    @Override
    public void showProgress() {
        searchPaginationAdapter.showProgress();
    }

    @Override
    public void hideProgress() {
        searchPaginationAdapter.hideProgress();
    }

    @Override
    public void initializeView() {
        recyclerView.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        findViewById(R.id.progressBarPagination).setVisibility(View.GONE);
    }

    @Override
    public void addList(List<PersonModel> list) {
        super.isLoading = false;
        if (searchView.isIconified()) {
            // the data is only adapted when the SearchView is not in use
            super.pageNumber++;
            searchPaginationAdapter.addList(list);
        }
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