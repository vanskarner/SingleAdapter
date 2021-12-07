package com.vanskarner.adapters.ui.multi_adapters;

import com.vanskarner.adapters.common.adaptersothers.AdapterItem;
import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.ui.BasePresenter;

import java.util.List;

interface MultiPaginationContract {

    interface view {

        void hideProgress();

        void addList(List<AdapterItem> list);

        void showNoPages();

    }

    interface presenter extends BasePresenter {

        void loadMore(int pageNumber);
    }
}
