package com.vanskarner.adapters.ui.simple_search;

import com.vanskarner.adapters.models.MovieModel;

import java.util.List;

interface SimpleSearchContract {

    interface view {
        void loadList(List<MovieModel> list);
    }

    interface presenter {
        void loadList();
    }

}
