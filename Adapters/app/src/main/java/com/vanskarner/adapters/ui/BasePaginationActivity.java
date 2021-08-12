package com.vanskarner.adapters.ui;

import android.os.Bundle;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BasePaginationActivity extends BaseActivity {

    protected int pageNumber = 1;
    protected boolean isLoading = false;
    protected boolean isFiltering = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAdapter();
        setupSearcher();
    }

    protected abstract void setupAdapter();

    protected abstract void setupSearcher();

    protected abstract int setLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager);

    protected abstract int setListSize();

    protected abstract void loadMore();

    protected RecyclerView.OnScrollListener recyclerOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (!isLoading && !isFiltering && manager != null &&
                        setLastVisibleItemPosition(manager) == setListSize() - 1) {
                    pageNumber++;
                    loadMore();
                    isLoading = true;
                }
            }
        };
    }

    protected SearchView.OnQueryTextListener searchViewOnQueryTextListener(Filter myFilter) {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isFiltering = true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                isFiltering = true;
                myFilter.filter(newText);
                return false;
            }
        };
    }

    protected SearchView.OnCloseListener searchViewCloseListener() {
        return () -> {
            isFiltering = false;
            return false;
        };
    }

}
