package com.jww.rereapp.product_detail.ui

import android.os.Bundle
import com.jww.rereapp.R
import com.jww.rereapp.base.BaseActivity

class ProductDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val input =
            intent?.extras?.getSerializable(ProductDetailActivity::class.simpleName) as? ProductDetailContract.Input
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ProductDetailFragment().apply {
                input?.let {
                    putArgument(it.productAdapterItem)
                }
            }).commit()
    }
}