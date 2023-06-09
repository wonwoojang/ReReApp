package com.jww.rereapp.re_evaluate

import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.item_model.ProductAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow

class ReEvaluateViewModel(
    productAdapterItem: ProductAdapterItem?
) : BaseViewModel() {
    val watchNumberTimesListData = listOf("1회차", "2회차", "3회차", "4회차", "5회차")
    val reasonListData = listOf("감독의 연출", "배우 연기력", "배경 음악", "시나리오", "기획 의도", "기타")
    val sexListData = listOf("남", "여")
    val ageListData = mutableListOf<String>().apply {
        for (i in 10..80) {
            this.add("$i 세")
        }
    }

    val bookAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.BookAdapterItem)

    val movieAdapterItem =
        MutableStateFlow(productAdapterItem as? ProductAdapterItem.MovieAdapterItem)

    val rating = MutableStateFlow(0.0f)

    var watchNumberSelected: String? = null
    var reasonTextList: MutableList<String>? = null
    var sexPosition: Int? = null
    var agePosition: Int? = null
}