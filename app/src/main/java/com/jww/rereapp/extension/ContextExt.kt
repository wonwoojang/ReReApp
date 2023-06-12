package com.jww.rereapp.extension

import android.content.Context
import android.graphics.Color

fun Context?.getColorListChart() = ArrayList<Int>().apply {
    add(Color.parseColor("#FFAE9F"))
    add(Color.parseColor("#FFCCAC"))
    add(Color.parseColor("#FB87AD"))
    add(Color.parseColor("#C2D1C0"))
    add(Color.parseColor("#70C1CA"))
    add(Color.parseColor("#ff5f67"))
    add(Color.parseColor("#3ca567"))
}