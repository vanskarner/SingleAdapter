package com.vanskarner.adapters.ui.multi_adapters;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.common.adaptersothers.BindItem;
import com.vanskarner.adapters.ui.NoPages;
import com.vanskarner.adapters.ui.multi_adapters.news.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

class MultiPaginationModel {
    private static final int TOTAL_PAGES = 4;

    public Single<List<BindItem>> sampleData(final int page) {
        return Single.just(true)
                .delay(4, TimeUnit.SECONDS)
                .map(value -> {
                    if (page > TOTAL_PAGES) {
                        throw new NoPages();
                    }
                    List<BindItem> list = new ArrayList<>();
                    list.add(new Person.PersonOne(R.drawable.img_1, "Sophia " + page));
                    list.add(new Person.PersonSecond(R.drawable.img_2, "Emma " + page));
                    list.add(new Person.PersonThird(R.drawable.img_3, "Isabella " + page, "23"));
                    list.add(new Person.PersonOne(R.drawable.img_4, "Olivia " + page));
                    list.add(new Person.PersonThird(R.drawable.img_5, "Ava " + page, "25"));
                    list.add(new Person.PersonSecond(R.drawable.img_6, "Emily " + page));
                    list.add(new Person.PersonThird(R.drawable.img_7, "Abigail " + page, "22"));
                    list.add(new Person.PersonOne(R.drawable.img_8, "Mia " + page));
                    list.add(new Person.PersonSecond(R.drawable.img_9, "Madison " + page));
                    list.add(new Person.PersonOne(R.drawable.img_10, "Elizabeth " + page));
                    list.add(new Person.PersonSecond(R.drawable.img_11, "Lindsay " + page));
                    list.add(new Person.PersonThird(R.drawable.img_12, "Valerie " + page, "25"));
                    list.add(new Person.PersonSecond(R.drawable.img_13, "Amara " + page));
                    list.add(new Person.PersonOne(R.drawable.img_14, "Leanne " + page));
                    list.add(new Person.PersonThird(R.drawable.img_15, "Charlotte " + page, "24"));
                    return list;
                });
    }

    public List<BindItem> sampleData2() {
        List<BindItem> list = new ArrayList<>();
        list.add(new Person.PersonOne(R.drawable.img_1, "Sophia "));
        list.add(new Person.PersonSecond(R.drawable.img_2, "Emma "));
        list.add(new Person.PersonThird(R.drawable.img_3, "Isabella ", "23"));
        list.add(new Person.PersonOne(R.drawable.img_4, "Olivia "));
        list.add(new Person.PersonThird(R.drawable.img_5, "Ava ", "25"));
        list.add(new Person.PersonSecond(R.drawable.img_6, "Emily "));
        list.add(new Person.PersonThird(R.drawable.img_7, "Abigail ", "22"));
        list.add(new Person.PersonOne(R.drawable.img_8, "Mia "));
        list.add(new Person.PersonSecond(R.drawable.img_9, "Madison "));
        list.add(new Person.PersonOne(R.drawable.img_10, "Elizabeth "));
        list.add(new Person.PersonSecond(R.drawable.img_11, "Lindsay "));
        list.add(new Person.PersonThird(R.drawable.img_12, "Valerie ", "25"));
        list.add(new Person.PersonSecond(R.drawable.img_13, "Amara "));
        list.add(new Person.PersonOne(R.drawable.img_14, "Leanne "));
        list.add(new Person.PersonThird(R.drawable.img_15, "Charlotte ", "24"));
        return list;
    }

}
