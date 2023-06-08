package com.jww.rereapp.product_detail.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.jww.rereapp.item_model.ProductAdapterItem

class ProductDetailContract : ActivityResultContract<ProductDetailContract.Input, Boolean>() {
    data class Input(val productAdapterItem: ProductAdapterItem?) : java.io.Serializable

    override fun createIntent(context: Context, input: Input): Intent {
        return Intent(context, ProductDetailActivity::class.java).apply {
            putExtra(ProductDetailActivity::class.java.simpleName, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        return true
    }
}