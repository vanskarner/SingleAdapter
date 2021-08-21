package com.vanskarner.adapters.ui.simple_pagination;

import com.vanskarner.adapters.models.MovieModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

class SimplePaginationModel {
    private static final int TOTAL_PAGES = 4;

    public Single<List<MovieModel>> sampleData(final int page) {
        return Single.just(true)
                .delay(4, TimeUnit.SECONDS)
                .map(value -> {
                    if (page > TOTAL_PAGES) {
                        throw new NoPages();
                    }
                    List<MovieModel> items = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        int id = (page * 10 + i);
                        items.add(new MovieModel(id, "Movie " + id));
                    }
                    return items;
                });
    }
}
