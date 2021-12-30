package com.utility.adapters.examples;

import androidx.annotation.NonNull;

import com.utility.adapters.singleadapter.BindItem;

public class WomanModel implements BindItem {
    private final int id;
    private final int imageID;
    private String firstName;
    private final Type type;

    public WomanModel(int id, int imageID, String firstName, Type type) {
        this.id = id;
        this.imageID = imageID;
        this.firstName = firstName;
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

    public void firstNameToUpperCase() {
        firstName = firstName.toUpperCase();
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ONE,
        SECOND
    }

    @NonNull
    @Override
    public String toString() {
        return "WomanModel{" +
                "id=" + id +
                ", imageID=" + imageID +
                ", firstName='" + firstName + '\'' +
                ", type=" + type +
                '}';
    }
}
