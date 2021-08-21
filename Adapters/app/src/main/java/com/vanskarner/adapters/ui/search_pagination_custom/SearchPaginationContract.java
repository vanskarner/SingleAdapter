package com.vanskarner.adapters.ui.search_pagination_custom;

import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

interface SearchPaginationContract {

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
