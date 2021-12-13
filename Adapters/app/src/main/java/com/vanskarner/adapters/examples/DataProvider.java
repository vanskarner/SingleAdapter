package com.vanskarner.adapters.examples;

import com.vanskarner.adapters.R;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<WomanModel> sampleData() {
        List<WomanModel> list = new ArrayList<>();
        list.add(new WomanModel(1, R.drawable.img_1, "Sophia", WomanModel.Type.ONE));
        list.add(new WomanModel(2, R.drawable.img_2, "Emma", WomanModel.Type.ONE));
        list.add(new WomanModel(3, R.drawable.img_3, "Isabella", WomanModel.Type.SECOND));
        list.add(new WomanModel(4, R.drawable.img_4, "Olivia", WomanModel.Type.ONE));
        list.add(new WomanModel(5, R.drawable.img_5, "Ava", WomanModel.Type.ONE));
        list.add(new WomanModel(6, R.drawable.img_6, "Emily", WomanModel.Type.ONE));
        list.add(new WomanModel(7, R.drawable.img_7, "Abigail", WomanModel.Type.ONE));
        list.add(new WomanModel(8, R.drawable.img_8, "Mia", WomanModel.Type.SECOND));
        list.add(new WomanModel(9, R.drawable.img_9, "Madison", WomanModel.Type.ONE));
        list.add(new WomanModel(10, R.drawable.img_10, "Elizabeth", WomanModel.Type.ONE));
        list.add(new WomanModel(11, R.drawable.img_11, "Lindsay", WomanModel.Type.SECOND));
        list.add(new WomanModel(12, R.drawable.img_12, "Valerie", WomanModel.Type.ONE));
        list.add(new WomanModel(13, R.drawable.img_13, "Amara", WomanModel.Type.ONE));
        list.add(new WomanModel(14, R.drawable.img_14, "Leanne", WomanModel.Type.SECOND));
        list.add(new WomanModel(15, R.drawable.img_15, "Charlotte", WomanModel.Type.ONE));
        return list;
    }

}
