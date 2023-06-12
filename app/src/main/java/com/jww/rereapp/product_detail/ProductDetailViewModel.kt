package com.jww.rereapp.product_detail

import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.PieEntry
import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import com.jww.rereapp.item_model.ProductAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(val productAdapterItem: ProductAdapterItem?) : BaseViewModel() {
    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()

    val bookAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.BookAdapterItem)

    val movieAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.MovieAdapterItem)

    val rating = MutableStateFlow(0.0f)

    private val sexListType = listOf("남", "여")
    private val ageListType = mutableListOf<String>().apply {
        for (i in 10..80) {
            if (i % 10 == 0)
                this.add("$i 세")
        }
    }

    fun requestSexData() {
//        서버에서 데이터 받을시

        viewModelScope.launch {
            val dataList = mutableListOf<Float>()
            sexListType.forEachIndexed { index, _ ->
                dataList.add(10 * (index + 1).toFloat())
            }
            val pieEntries = sexListType.zip(dataList) { typeKey, value ->
                PieEntry(value, typeKey)
            }

            eventFlow.emit(Event.ResultSexListData(pieEntries))
        }
    }

    fun requestAgeData() {
//        서버에서 데이터 받을시
        viewModelScope.launch {
            val dataList = mutableListOf<Float>()
            ageListType.forEachIndexed { index, key ->
                dataList.add(10 * (index + 1).toFloat())
            }

            val pieEntries = ageListType.zip(dataList) { typeKey, value ->
                PieEntry(value, typeKey)
            }
            eventFlow.emit(Event.ResultAgeListData(pieEntries))
        }
    }

    sealed class Event {
        class ResultSexListData(val resultList: List<PieEntry>) : Event()
        class ResultAgeListData(val resultList: List<PieEntry>) : Event()
    }
}