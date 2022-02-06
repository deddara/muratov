package com.example.aaaa.ui.home

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.aaaa.R
import com.example.aaaa.enumiration.GifApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide
            .with(imgView.context)
            .load(imgUrl)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("gifApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: GifApiStatus?) {
    when (status) {
        GifApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        GifApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        GifApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}