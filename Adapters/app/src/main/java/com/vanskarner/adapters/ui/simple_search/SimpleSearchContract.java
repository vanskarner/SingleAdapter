package com.vanskarner.adapters.ui.simple_search;

import com.vanskarner.adapters.models.PersonModel;

import java.util.List;

interface SimpleSearchContract {

    interface view {
        void addList(List<PersonModel> list);
    }

    interface presenter {
        void loadList();
    }

}
