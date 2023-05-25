package com.jww.rereapp.reEvaluate.ui

import android.os.Bundle
import com.jww.rereapp.R
import com.jww.rereapp.base.BaseActivity


class ReEvaluateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val input =
            intent?.extras?.getSerializable(ReEvaluateActivity::class.simpleName) as? ReEvaluateContract.Input
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ReEvaluateFragment().apply {
                input?.let {
                    putArgument(it.contentsType, it.bookAdapterItem)
                }
            }).commit()
    }
}