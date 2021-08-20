package com.vanskarner.adapters.models;

import androidx.annotation.NonNull;

public class MovieModel {
    private final int id;
    private final String name;

    public MovieModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
