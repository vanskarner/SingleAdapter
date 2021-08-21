package com.vanskarner.adapters.ui.simple_search;

import com.vanskarner.adapters.models.MovieModel;

import java.util.ArrayList;
import java.util.List;


class SimpleSearchModel {

    public List<MovieModel> sampleData() {
        List<MovieModel> items = new ArrayList<>();
        items.add(new MovieModel(1, "The Night Comes for Us"));
        items.add(new MovieModel(2, "Hero"));
        items.add(new MovieModel(3, "Fast & Furious 7"));
        items.add(new MovieModel(4, "Matrix"));
        items.add(new MovieModel(5, "Kung Fu Sion"));
        items.add(new MovieModel(6, "Deadpool"));
        items.add(new MovieModel(7, "Minority Report"));
        items.add(new MovieModel(8, "Kill Bill"));
        items.add(new MovieModel(9, "Operation Mekong"));
        items.add(new MovieModel(10, "Bad Boys"));
        items.add(new MovieModel(11, "The Tomorrow War"));
        items.add(new MovieModel(12, "Black Widow"));
        items.add(new MovieModel(13, "Tenet"));
        items.add(new MovieModel(14, "Venom"));
        items.add(new MovieModel(15, "Lucy"));
        return items;
    }
}
