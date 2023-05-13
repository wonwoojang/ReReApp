package com.jww.rereapp.extension

import android.view.View


class OnThrottleClickListener(
    private val interval: Long,
    private val action: (View) -> Unit
) : View.OnClickListener {
    private var clickable = true
    override fun onClick(v: View) {
        if (clickable && v.isEnabled) {
            clickable = false
            v.run {
                postDelayed({ clickable = true }, interval)
                action(v)
            }
        }
    }
}