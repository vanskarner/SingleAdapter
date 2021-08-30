package com.vanskarner.adapters.models;

import androidx.annotation.NonNull;

public class PersonModel {
    private final int id;
    private final String name;
    private final String image;

    public PersonModel(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @NonNull
    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
