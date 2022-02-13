package com.amandeep.kotlincoroutinesexamples.view.newFeeds

import android.widget.ImageView
import com.amandeep.kotlincoroutinesexamples.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object utils {

    fun ImageView.loadImage(uri: String?) {
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher)
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }
}