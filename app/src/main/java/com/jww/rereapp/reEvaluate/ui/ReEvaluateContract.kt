package com.jww.rereapp.reEvaluate.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.jww.rereapp.enums.ContentsType
import com.jww.rereapp.itemModel.BookAdapterItem

class ReEvaluateContract : ActivityResultContract<ReEvaluateContract.Input, Boolean>() {
    data class Input(
        val contentsType: ContentsType,
        val bookAdapterItem: BookAdapterItem?
    ) : java.io.Serializable

    override fun createIntent(context: Context, input: Input): Intent {
        return Intent(context, ReEvaluateActivity::class.java).apply {
            putExtra(ReEvaluateActivity::class.java.simpleName, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return true
    }
}