package com.vanskarner.adapters.ui;

import com.vanskarner.adapters.MovieModel;

import java.util.List;

public interface MoviePaginationContract {

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
