package com.vanskarner.adapters.examples.progress;

import com.vanskarner.adapters.R;
import com.vanskarner.adapters.examples.simple.WomanModel;
import com.vanskarner.adapters.ui.NoPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

class DataProvider {
    private static final int TOTAL_PAGES = 4;

    public static Single<List<WomanModel>> sampleData(final int page) {
        return Single.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(aBoolean -> {
                    if (page > TOTAL_PAGES) {
                        throw new NoPages();
                    }
                    List<WomanModel> list = new ArrayList<>();
                    list.add(new WomanModel(1, R.drawable.img_1, "Sophia - " + page, "20", WomanModel.Type.ONE));
                    list.add(new WomanModel(2, R.drawable.img_2, "Emma - " + page, "22", WomanModel.Type.ONE));
                    list.add(new WomanModel(3, R.drawable.img_3, "Isabella - " + page, "23", WomanModel.Type.SECOND));
                    list.add(new WomanModel(4, R.drawable.img_4, "Olivia - " + page, "26", WomanModel.Type.ONE));
                    list.add(new WomanModel(5, R.drawable.img_5, "Ava - " + page, "25", WomanModel.Type.ONE));
                    list.add(new WomanModel(6, R.drawable.img_6, "Emily - " + page, "21", WomanModel.Type.ONE));
                    list.add(new WomanModel(7, R.drawable.img_7, "Abigail - " + page, "22", WomanModel.Type.ONE));
                    list.add(new WomanModel(8, R.drawable.img_8, "Mia - " + page, "24", WomanModel.Type.SECOND));
                    list.add(new WomanModel(9, R.drawable.img_9, "Madison - " + page, "20", WomanModel.Type.ONE));
                    list.add(new WomanModel(10, R.drawable.img_10, "Elizabeth - " + page, "26", WomanModel.Type.ONE));
                    list.add(new WomanModel(11, R.drawable.img_11, "Lindsay - " + page, "23", WomanModel.Type.SECOND));
                    list.add(new WomanModel(12, R.drawable.img_12, "Valerie - " + page, "25", WomanModel.Type.ONE));
                    list.add(new WomanModel(13, R.drawable.img_13, "Amara - " + page, "22", WomanModel.Type.ONE));
                    list.add(new WomanModel(14, R.drawable.img_14, "Leanne - " + page, "20", WomanModel.Type.SECOND));
                    list.add(new WomanModel(15, R.drawable.img_15, "Charlotte - " + page, "24", WomanModel.Type.ONE));
                    return list;
                });
    }

    public static List<WomanModel> sampleData2(){
        List<WomanModel> list = new ArrayList<>();
        list.add(new WomanModel(1, R.drawable.img_1, "Sophia - " + 0, "20", WomanModel.Type.ONE));
        list.add(new WomanModel(2, R.drawable.img_2, "Emma - " + 0, "22", WomanModel.Type.ONE));
        list.add(new WomanModel(3, R.drawable.img_3, "Isabella - " + 0, "23", WomanModel.Type.SECOND));
        list.add(new WomanModel(4, R.drawable.img_4, "Olivia - " + 0, "26", WomanModel.Type.ONE));
        list.add(new WomanModel(5, R.drawable.img_5, "Ava - " + 0, "25", WomanModel.Type.ONE));
        list.add(new WomanModel(6, R.drawable.img_6, "Emily - " + 0, "21", WomanModel.Type.ONE));
        list.add(new WomanModel(7, R.drawable.img_7, "Abigail - " + 0, "22", WomanModel.Type.ONE));
        list.add(new WomanModel(8, R.drawable.img_8, "Mia - " + 0, "24", WomanModel.Type.SECOND));
        list.add(new WomanModel(9, R.drawable.img_9, "Madison - " + 0, "20", WomanModel.Type.ONE));
        list.add(new WomanModel(10, R.drawable.img_10, "Elizabeth - " + 0, "26", WomanModel.Type.ONE));
        list.add(new WomanModel(11, R.drawable.img_11, "Lindsay - " + 0, "23", WomanModel.Type.SECOND));
        list.add(new WomanModel(12, R.drawable.img_12, "Valerie - " + 0, "25", WomanModel.Type.ONE));
        list.add(new WomanModel(13, R.drawable.img_13, "Amara - " + 0, "22", WomanModel.Type.ONE));
        list.add(new WomanModel(14, R.drawable.img_14, "Leanne - " + 0, "20", WomanModel.Type.SECOND));
        list.add(new WomanModel(15, R.drawable.img_15, "Charlotte - " + 0, "24", WomanModel.Type.ONE));
        return list;
    }
}
