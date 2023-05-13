package com.jww.rereapp.extension

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter("invisible")
fun View.setInvisible(invisible: Boolean) {
    isInvisible = invisible
}


fun View.throttleClick(interval: Long? = null, action: (View) -> Unit) {
    setOnClickListener(OnThrottleClickListener(interval ?: 100, action))
}

fun View.debounceClick(waiting: Long? = null, action: (View, debounce: Boolean) -> Unit) {
    setOnClickListener(OnDebounceClickListener(waiting ?: 500, action))
}