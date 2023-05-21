package com.jww.rereapp.reEvaluate.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ReEvaluateContract : ActivityResultContract<String, Boolean>() {
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context, ReEvaluateActivity::class.java).apply {
            putExtra(ReEvaluateActivity::class.java.simpleName, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return true
    }
}