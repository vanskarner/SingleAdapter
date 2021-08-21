package com.vanskarner.adapters.ui.simple_pagination;

import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

interface SimplePaginationContract {
    interface view {
        void showProgress();

        void hideProgress();

        void initializeView();

        void addList(List<MovieModel> list);

        void showNoPages();
    }

    interface presenter {

        void loadMore(int pageNumber);

        void onDestroy();
    }
}
