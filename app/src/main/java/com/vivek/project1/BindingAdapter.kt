package com.vivek.project1

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgUrl")
fun loadImage(view: ImageView, url: String) {
    Glide
        .with(view.context)
        .load(url)
        .centerCrop()
        .into(view);
}