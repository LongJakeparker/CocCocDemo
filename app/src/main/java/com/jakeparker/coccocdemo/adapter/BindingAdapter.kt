package com.jakeparker.coccocdemo.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakeparker.coccocdemo.util.CommonUtils

/**
 * @author Long Tran
 * @since 02/01/2021
 */

@BindingAdapter("android:image")
fun bindImage(view: ImageView, image: String?) {
    if (image.isNullOrEmpty()) {
        view.setBackgroundResource(android.R.color.darker_gray)
        return
    }
    CommonUtils.loadImage(image, view)
}

@BindingAdapter("android:onRefreshListener")
fun bindRefreshListener(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener == null) {
        return
    }

    view.setOnRefreshListener(listener)
}