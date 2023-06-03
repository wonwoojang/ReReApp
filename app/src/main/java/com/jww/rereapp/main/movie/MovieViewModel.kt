package com.jww.rereapp.main.movie

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.asEventFlow
import com.jww.rereapp.common.mutableEventFlow
import com.jww.rereapp.item_model.MovieAdapterItem
import kotlinx.coroutines.flow.MutableStateFlow

class MovieViewModel(private val useCase: MovieUseCase) : BaseViewModel() {

    private val eventFlow = mutableEventFlow<Event>()
    fun eventFlow() = eventFlow.asEventFlow()

    val input = MutableStateFlow("")
    val totalCount = MutableStateFlow(0)

    fun searchMovieFlow() = Pager(config = PagingConfig(pageSize = 10)) {
        MoviePagingSource(useCase, input.value)
    }.flow.cachedIn(viewModelScope)

    sealed class Event {
    }

    inner class MoviePagingSource(
        private val useCase: MovieUseCase,
        private val searchWord: String?
    ) :
        PagingSource<Int, MovieAdapterItem>() {
        override fun getRefreshKey(state: PagingState<Int, MovieAdapterItem>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieAdapterItem> {
            return try {
                val nextPageNumber = params.key ?: 0
                val response =
                    useCase.searchMoviePaging(searchWord, nextPageNumber * 10).getOrNull()?.body()
                val result = response?.data?.firstOrNull()?.result
                val totalCount = response?.totalCount ?: 0
                (this@MovieViewModel).totalCount.emit(totalCount)
                LoadResult.Page(
                    data = result!!.map {
                        MovieAdapterItem(
                            it.movieSeq,
                            it.title,
                            it.prodYear,
                            it.posters
                        )
                    },
                    prevKey = null,
                    nextKey = if ((totalCount % 10) >= nextPageNumber) nextPageNumber + 1 else null
                )

            } catch (e: java.lang.Exception) {
                LoadResult.Error(e)
            }
        }
    }
}