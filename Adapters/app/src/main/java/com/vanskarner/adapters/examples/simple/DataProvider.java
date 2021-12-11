package com.vanskarner.adapters.examples.simple;

import com.vanskarner.adapters.R;

import java.util.ArrayList;
import java.util.List;

class DataProvider {

    public static List<WomanModel> sampleData() {
        List<WomanModel> list = new ArrayList<>();
        list.add(new WomanModel(1, R.drawable.img_1, "Sophia", "20", WomanModel.Type.ONE));
        list.add(new WomanModel(2, R.drawable.img_2, "Emma", "22", WomanModel.Type.ONE));
        list.add(new WomanModel(3, R.drawable.img_3, "Isabella", "23", WomanModel.Type.SECOND));
        list.add(new WomanModel(4, R.drawable.img_4, "Olivia", "26", WomanModel.Type.ONE));
        list.add(new WomanModel(5, R.drawable.img_5, "Ava", "25", WomanModel.Type.ONE));
        list.add(new WomanModel(6, R.drawable.img_6, "Emily", "21", WomanModel.Type.ONE));
        list.add(new WomanModel(7, R.drawable.img_7, "Abigail", "22", WomanModel.Type.ONE));
        list.add(new WomanModel(8, R.drawable.img_8, "Mia", "24", WomanModel.Type.SECOND));
        list.add(new WomanModel(9, R.drawable.img_9, "Madison", "20", WomanModel.Type.ONE));
        list.add(new WomanModel(10, R.drawable.img_10, "Elizabeth", "26", WomanModel.Type.ONE));
        list.add(new WomanModel(11, R.drawable.img_11, "Lindsay", "23", WomanModel.Type.SECOND));
        list.add(new WomanModel(12, R.drawable.img_12, "Valerie", "25", WomanModel.Type.ONE));
        list.add(new WomanModel(13, R.drawable.img_13, "Amara", "22", WomanModel.Type.ONE));
        list.add(new WomanModel(14, R.drawable.img_14, "Leanne", "20", WomanModel.Type.SECOND));
        list.add(new WomanModel(15, R.drawable.img_15, "Charlotte", "24", WomanModel.Type.ONE));
        return list;
    }

}
