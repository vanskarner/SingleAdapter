package com.vanskarner.adapters.ui.simple_pagination;

import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

interface SimplePaginationContract {
    interface view {
        void showProgress();

        void hideProgress();

        void initializeView();

        void addList(List<PersonModel> list);

        void showNoPages();
    }

    interface presenter {

        void loadMore(int pageNumber);

        void onDestroy();
    }
}
