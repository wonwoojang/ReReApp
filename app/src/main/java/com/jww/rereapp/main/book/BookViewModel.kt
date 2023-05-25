package com.jww.rereapp.main.book

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import com.jww.rereapp.itemModel.BookAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow

class BookViewModel(private val useCase: BookUseCase) : BaseViewModel() {
    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()

    val input = MutableStateFlow("")

    val totalCount = MutableStateFlow(0)

    fun searchBookFlow() = Pager(config = PagingConfig(pageSize = 10)) {
        BookPagingSource(useCase, input.value)
    }.flow.cachedIn(viewModelScope)


    inner class BookPagingSource(
        private val useCase: BookUseCase,
        private val searchWord: String?
    ) : PagingSource<Int, BookAdapterItem>() {
        override fun getRefreshKey(state: PagingState<Int, BookAdapterItem>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookAdapterItem> {
            return try {
                val nextPageNumber = params.key ?: 0

                val displayCount = 20

                val response =
                    useCase.searchList(
                        searchWord,
                        (nextPageNumber * displayCount) + 1,
                        displayCount
                    ).getOrNull()?.body()
                totalCount.emit(response?.total ?: 0)
                val result = response?.items?.map {
                    BookAdapterItem(
                        id = it.isbn,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        author = it.author
                    )
                }

                LoadResult.Page(
                    data = result!!,
                    prevKey = null,
                    nextKey = if (totalCount.value > (response.start + response.items.size)) nextPageNumber + 1 else null
                )
            } catch (e: java.lang.Exception) {
                LoadResult.Error(e)
            }
        }
    }

    class Event() {

    }
}