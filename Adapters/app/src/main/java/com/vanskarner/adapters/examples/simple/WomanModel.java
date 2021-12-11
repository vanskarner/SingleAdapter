package com.vanskarner.adapters.examples.simple;

import com.vanskarner.adapters.common.adaptersothers.BindItem;

public class WomanModel implements BindItem<Integer> {
    private final int id;
    private final int imageID;
    private final String firstName;
    private final String age;
    private final Type type;

    public WomanModel(int id, int imageID, String firstName, String age, Type type) {
        this.id = id;
        this.imageID = imageID;
        this.firstName = firstName;
        this.age = age;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    @Override
    public Integer bindItemID() {
        return id;
    }

    enum Type {
        ONE,
        SECOND
    }

}
