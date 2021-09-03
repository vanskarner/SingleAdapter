package com.vanskarner.adapters.ui.simple_search;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.models.PersonModel;

import java.util.ArrayList;
import java.util.List;

class SimpleSearchModel {

    public List<PersonModel> sampleData() {
        List<PersonModel> items = new ArrayList<>();
        items.add(new PersonModel( "Sophia", R.drawable.img_1));
        items.add(new PersonModel( "Emma", R.drawable.img_2));
        items.add(new PersonModel("Isabella", R.drawable.img_3));
        items.add(new PersonModel( "Olivia", R.drawable.img_4));
        items.add(new PersonModel( "Ava", R.drawable.img_5));
        items.add(new PersonModel( "Emily", R.drawable.img_6));
        items.add(new PersonModel( "Abigail", R.drawable.img_7));
        items.add(new PersonModel( "Mia", R.drawable.img_8));
        items.add(new PersonModel("Madison", R.drawable.img_9));
        items.add(new PersonModel( "Elizabeth", R.drawable.img_10));
        items.add(new PersonModel( "Lindsay", R.drawable.img_11));
        items.add(new PersonModel( "Valerie", R.drawable.img_12));
        items.add(new PersonModel("Amara", R.drawable.img_13));
        items.add(new PersonModel( "Leanne", R.drawable.img_14));
        items.add(new PersonModel( "Charlotte", R.drawable.img_15));
        return items;
    }
}
