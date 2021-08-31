package com.vanskarner.adapters.ui.search_pagination;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.models.PersonModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

class SearchPaginationModel {

    private static final int TOTAL_PAGES = 4;

    public Single<List<PersonModel>> sampleData(final int page) {
        return Single.just(true)
                .delay(4, TimeUnit.SECONDS)
                .map(value -> {
                    if (page > TOTAL_PAGES) {
                        throw new NoPages();
                    }
                    List<PersonModel> items = new ArrayList<>();
                    items.add(new PersonModel("Sophia " + page, R.drawable.img_1));
                    items.add(new PersonModel( "Emma " + page, R.drawable.img_2));
                    items.add(new PersonModel( "Isabella " + page, R.drawable.img_3));
                    items.add(new PersonModel( "Olivia " + page, R.drawable.img_4));
                    items.add(new PersonModel( "Ava " + page, R.drawable.img_5));
                    items.add(new PersonModel( "Emily " + page, R.drawable.img_6));
                    items.add(new PersonModel( "Abigail " + page, R.drawable.img_7));
                    items.add(new PersonModel( "Mia " + page, R.drawable.img_8));
                    items.add(new PersonModel( "Madison " + page, R.drawable.img_9));
                    items.add(new PersonModel( "Elizabeth " + page, R.drawable.img_10));
                    items.add(new PersonModel( "Lindsay " + page, R.drawable.img_11));
                    items.add(new PersonModel("Valerie " + page, R.drawable.img_12));
                    items.add(new PersonModel( "Amara " + page, R.drawable.img_13));
                    items.add(new PersonModel( "Leanne " + page, R.drawable.img_14));
                    items.add(new PersonModel("Charlotte " + page, R.drawable.img_15));
                    return items;
                });
    }

}
