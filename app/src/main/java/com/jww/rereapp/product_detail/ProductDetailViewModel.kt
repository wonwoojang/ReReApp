package com.jww.rereapp.productDetail

import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow

class ProductDetailViewModel : BaseViewModel() {
    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()

    sealed class Event {

    }
}