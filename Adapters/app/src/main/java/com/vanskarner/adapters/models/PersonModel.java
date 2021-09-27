package com.vanskarner.adapters.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class PersonModel {
    private final String name;
    private final int image;

    public PersonModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    @BindingAdapter("imageLoader")
    public static void setImage(ShapeableImageView imageView, int resource) {
        Glide.with(imageView).load(resource).into(imageView);
    }

    @BindingAdapter("imageLoaderGrid")
    public static void setImageGrid(ImageView imageView, int resource) {
        Glide.with(imageView).load(resource).into(imageView);
    }

    @NonNull
    @Override
    public String toString() {
        return "PersonModel{" +
                "name='" + name +
                '}';
    }
}
