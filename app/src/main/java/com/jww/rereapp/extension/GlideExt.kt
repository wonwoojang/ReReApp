package com.jww.rereapp.extension

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.jww.rereapp.di.GlideApp


private fun <T> RequestBuilder<T>.options(options: RequestOptions?) = options?.let {
    apply(it)
} ?: this

@BindingAdapter(
    value = ["glide_uri", "glide_options"],
    requireAll = false
)
fun ImageView.glide(uri: Uri?, options: RequestOptions? = null) {
    GlideApp.with(this)
        .load(uri)
        .options(options)
        .into(this)
}

@BindingAdapter(
    value = ["glide_url", "glide_options"], requireAll = false
)
fun ImageView.glide(url: String?, options: RequestOptions? = null) {
    GlideApp.with(this)
        .load(url)
        .options(options)
        .into(this)
}