package com.vanskarner.adapters.ui;

import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BasePaginationActiviy extends AppCompatActivity {

    protected int pageNumber = 1;
    protected boolean isLoading = false;
    protected boolean isFiltering = false;

    protected abstract int lastVisibleItemPosition();

    protected abstract void loadMore();

    protected abstract int setListSize();

    protected RecyclerView.OnScrollListener recyclerOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && !isFiltering && recyclerView.getLayoutManager() != null &&
                        lastVisibleItemPosition() == setListSize() - 1) {
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
                isFiltering = true;//*
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


}
