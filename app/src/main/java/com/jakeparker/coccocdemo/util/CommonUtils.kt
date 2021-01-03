package com.jakeparker.coccocdemo.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Long Tran
 * @since 02/01/2021
 */
object CommonUtils {
    var mClickTimeInterval: Long = 800
    var mLastClickTime: Long = 0

    //return true if click too fast
    fun checkClickTimeInvalidation(): Boolean {
        val now = System.currentTimeMillis()
        if (now - mLastClickTime < mClickTimeInterval) {
            return true
        }
        mLastClickTime = now
        return false
    }

    fun loadImage(url: String?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun parseDateTime(dateStr: String?): String {
        if (dateStr.isNullOrEmpty())
            return ""

        val sdfOrigin = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US)
        val date = sdfOrigin.parse(dateStr)

        val sdf = SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm", Locale("vi", "VN"))
        return sdf.format(date?.time)
    }
}