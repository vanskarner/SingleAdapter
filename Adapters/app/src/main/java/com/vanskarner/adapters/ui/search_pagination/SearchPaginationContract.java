package com.vanskarner.adapters.ui.search_pagination;

import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

interface SearchPaginationContract {

    interface view {

        void hideProgress();

        void addList(List<PersonModel> list);

        void showNoPages();
    }

    interface presenter {

        void loadMore(int pageNumber);

        void onDestroy();
    }

}
