package com.vanskarner.adapters;

public class MovieModel {
    private final int id;
    private final String title;
    private final String image;

    public MovieModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
