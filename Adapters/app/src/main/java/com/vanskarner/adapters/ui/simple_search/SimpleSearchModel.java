package com.vanskarner.adapters.ui.simple_search;

import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;


class SimpleSearchModel {

    public List<PersonModel> sampleData() {
        List<PersonModel> items = new ArrayList<>();
        items.add(new PersonModel(1, "The Night Comes for Us", "IMG"));
        items.add(new PersonModel(2, "Hero", "IMG"));
        items.add(new PersonModel(3, "Fast & Furious 7", "IMG"));
        items.add(new PersonModel(4, "Matrix", "IMG"));
        items.add(new PersonModel(5, "Kung Fu Sion", "IMG"));
        items.add(new PersonModel(6, "Deadpool", "IMG"));
        items.add(new PersonModel(7, "Minority Report", "IMG"));
        items.add(new PersonModel(8, "Kill Bill", "IMG"));
        items.add(new PersonModel(9, "Operation Mekong", "IMG"));
        items.add(new PersonModel(10, "Bad Boys", "IMG"));
        items.add(new PersonModel(11, "The Tomorrow War", "IMG"));
        items.add(new PersonModel(12, "Black Widow", "IMG"));
        items.add(new PersonModel(13, "Tenet", "IMG"));
        items.add(new PersonModel(14, "Venom", "IMG"));
        items.add(new PersonModel(15, "Lucy", "IMG"));
        return items;
    }
}
