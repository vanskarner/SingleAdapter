package com.utility.adapters.examples;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("imageLoader")
    public static void setImageGrid(ImageView imageView, int resource) {
        Glide.with(imageView).load(resource).into(imageView);
    }

}
