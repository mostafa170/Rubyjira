package com.devartlab.rubyjira.data.utils

import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devartlab.rubyjira.R

@BindingAdapter("myImageResource")
fun bindImageViewResourceId(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}



@BindingAdapter("app:tint")
fun ImageView.setImageTint(@ColorInt color: Int) {
    setColorFilter(color)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}


@BindingAdapter("imageUrlCircle")
fun bindImageViewWithCircleGlideUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url)
        .error(R.drawable.user_logo)
        .circleCrop().into(imageView)
}




