package com.vanskarner.adapters.ui.bases;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationActivity extends BaseActivity {

    protected int pageNumber = 1;
    protected boolean isLoading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAdapter();
    }

    protected abstract void setupAdapter();

    protected abstract int setLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager);

    protected abstract int setListSize();

    protected abstract void loadMore();

    protected RecyclerView.OnScrollListener recyclerOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (!isLoading && manager != null &&
                        setLastVisibleItemPosition(manager) == setListSize() - 1) {
                    pageNumber++;
                    loadMore();
                    isLoading = true;
                }
            }
        };
    }

}
