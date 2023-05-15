package com.jww.rereapp.main.webToon

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import com.jww.rereapp.itemModel.WebToonAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow

class WebToonViewModel(private val useCase: WebToonUseCase) : BaseViewModel() {
    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()

    val input = MutableStateFlow("")

    val totalCount = MutableStateFlow(0)

    fun searchWebToonFlow() = Pager(config = PagingConfig(pageSize = 10)) {
        WebToonPagingSource(useCase, input.value)
    }.flow.cachedIn(viewModelScope)


    inner class WebToonPagingSource(
        private val useCase: WebToonUseCase,
        private val searchWord: String?
    ) : PagingSource<Int, WebToonAdapterItem>() {
        override fun getRefreshKey(state: PagingState<Int, WebToonAdapterItem>): Int? {
            TODO("Not yet implemented")
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WebToonAdapterItem> {
            return try {
                val nextPageNumber = params.key ?: 1
                val response =
                    useCase.searchList(searchWord, nextPageNumber).getOrNull()?.body()

                totalCount.emit(response?.result?.totalCount ?: 0)

                val result = response?.itemList?.map {
                    WebToonAdapterItem(it)
                }

                LoadResult.Page(
                    data = result!!,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
                )
            } catch (e: java.lang.Exception) {
                LoadResult.Error(e)
            }
        }
    }

    class Event() {

    }
}