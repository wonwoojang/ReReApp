package com.jww.rereapp.extension

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.jww.rereapp.R

fun Context?.getColorListChart() = ArrayList<Int>().apply {
    add(Color.parseColor("#FFAE9F"))
    add(Color.parseColor("#FFCCAC"))
    add(Color.parseColor("#FB87AD"))
    add(Color.parseColor("#C2D1C0"))
    add(Color.parseColor("#70C1CA"))
    add(Color.parseColor("#ff5f67"))
    add(Color.parseColor("#3ca567"))
}

fun Context?.alertDialog(build: (AlertDialog.Builder.() -> Unit)) = this?.let {
    val builder = AlertDialog.Builder(it, R.style.alert_dialog_form)
    build(builder)
    builder.show().apply {
        findViewById<TextView>(android.R.id.message)?.let {
            it.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        }
    }
}