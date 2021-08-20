package com.vanskarner.adapters.ui.simple_pagination;

import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

public interface PaginationSimpleContract {
    interface view {
        void showProgress();

        void hideProgress();

        void showNecessaryViews();

        void addList(List<MovieModel> list);

        void showNoPages();
    }

    interface presenter {

        void loadMore(int pageNumber);

        void onDestroy();
    }
}
