package com.vanskarner.adapters.examples;

import com.vanskarner.adapters.R;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<WomanModel> sampleData() {
        return sampleDataMsg("");
    }

    public static List<WomanModel> sampleDataMsg(String msg) {
        List<WomanModel> list = new ArrayList<>();
        list.add(new WomanModel(1, R.drawable.img_1, "Sophia" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(2, R.drawable.img_2, "Emma" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(3, R.drawable.img_3, "Isabella" + msg, WomanModel.Type.SECOND));
        list.add(new WomanModel(4, R.drawable.img_4, "Olivia" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(5, R.drawable.img_5, "Ava" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(6, R.drawable.img_6, "Emily" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(7, R.drawable.img_7, "Abigail" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(8, R.drawable.img_8, "Mia" + msg, WomanModel.Type.SECOND));
        list.add(new WomanModel(9, R.drawable.img_9, "Madison" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(10, R.drawable.img_10, "Elizabeth" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(11, R.drawable.img_11, "Lindsay" + msg, WomanModel.Type.SECOND));
        list.add(new WomanModel(12, R.drawable.img_12, "Valerie" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(13, R.drawable.img_13, "Amara" + msg, WomanModel.Type.ONE));
        list.add(new WomanModel(14, R.drawable.img_14, "Leanne" + msg, WomanModel.Type.SECOND));
        list.add(new WomanModel(15, R.drawable.img_15, "Charlotte" + msg, WomanModel.Type.ONE));
        return list;
    }

}
