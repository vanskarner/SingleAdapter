package com.vanskarner.adapters.examples.simple;

import com.vanskarner.adapters.common.adaptersothers.BindItem;

class WomanModel implements BindItem<Integer> {
    private final int id;
    private final int imageID;
    private final String firstName;
    private final String age;
    private final boolean premium;

    public WomanModel(int id, int imageID, String firstName, String age, boolean premium) {
        this.id = id;
        this.imageID = imageID;
        this.firstName = firstName;
        this.age = age;
        this.premium = premium;
    }

    public int getId() {
        return id;
    }

    public int getImageID() {
        return imageID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }

    public boolean isPremium() {
        return premium;
    }

    @Override
    public Integer bindItemID() {
        return id;
    }

}
