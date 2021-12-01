package com.vanskarner.adapters.ui.multi_adapters;

import com.vanskarner.adapters.ui.BasePresenter;
import com.vanskarner.adapters.ui.multi_adapters.news.Person;

import java.util.List;

interface MultiPaginationContract {

    interface view {

        void addList(List<Person> list);

    }

    interface presenter {

        void loadMore();
    }
}
