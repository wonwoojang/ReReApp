package com.jww.rereapp.extension

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnDebounceClickListener(
    private val waiting: Long,
    private val action: (
        view: View, debounce: Boolean
    ) -> Unit
) : View.OnClickListener {
    private var job: Job? = null
    override fun onClick(v: View) {
        job?.cancel()
        action(v, false)
        job = (v.context as? AppCompatActivity)?.lifecycleScope?.launch {
            delay(waiting)
            action(v, true)
        }
    }
}