package com.amitesh.webskittertestproject.utility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("image_url")
fun loadImageUrl(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return
    trycatch {
        Picasso.get().load(imageUrl).into(imageView)
    }
}