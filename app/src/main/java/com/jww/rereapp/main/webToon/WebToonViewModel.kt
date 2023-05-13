package com.jww.rereapp.main.webToon

import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import kotlinx.coroutines.flow.MutableStateFlow

class WebToonViewModel(val useCase: WebToonUseCase) : BaseViewModel() {
    private val _eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = _eventFlow.asEventFlow()

    val input = MutableStateFlow("")

//
//    fun searchWebToonFlow() = Pager(config = PagingConfig(pageSize = 10)) {
//        PagingSource<Int, WebToonAdapterItem>()
//    }.flow.cachedIn(viewModelScope)

    class Event() {

    }
}