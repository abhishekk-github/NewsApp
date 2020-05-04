package com.genius.amateur.alpha.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    if (url == null) return
    Glide.with(view)
        .load(url)
        .into(view)
}