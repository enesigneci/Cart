package com.enesigneci.basket.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.enesigneci.basket.R

fun ImageView.loadFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}