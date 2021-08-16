package com.vanskarner.adapters.ui;

import com.vanskarner.adapters.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class MoviePaginationInteractor {

    public Single<List<MovieModel>> dataFromNetwork(final int page) {
        return Single.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(value -> {
                    List<MovieModel> items = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        items.add(new MovieModel(i, "Item " + (page * 10 + i), "IMG"));
                    }
                    return items;
                });
    }

}
