package com.jww.rereapp.product_detail

import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import com.jww.rereapp.item_model.ProductAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow

class ProductDetailViewModel(val productAdapterItem: ProductAdapterItem?) : BaseViewModel() {
    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()


    val bookAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.BookAdapterItem)

    val movieAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.MovieAdapterItem)

    sealed class Event {

    }
}