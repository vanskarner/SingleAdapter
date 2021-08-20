package com.vanskarner.adapters.common.bases;

import android.os.Bundle;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SearchPaginationActivity extends PaginationActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchView searchView = setSearchView();
        searchView.setOnQueryTextListener(searchViewOnQueryTextListener());
    }

    @Override
    protected RecyclerView.OnScrollListener recyclerOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (!isLoading && setSearchView().isIconified() && manager != null &&
                        setLastVisibleItemPosition(manager) == manager.getItemCount() - 1) {
                    loadMore();
                    isLoading = true;
                }
            }
        };
    }

    protected abstract SearchView setSearchView();

    protected abstract Filter setFilter();

    protected SearchView.OnQueryTextListener searchViewOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Filter filter = setFilter();
                filter.filter(newText);
                return false;
            }
        };
    }

}
