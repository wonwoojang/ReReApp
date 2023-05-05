package com.jww.rereapp.main.movie

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jww.rereapp.base.BaseViewModel
import com.jww.rereapp.common.models.Movie
import com.jww.rereapp.main.movie.ui.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MovieUseCase) : BaseViewModel() {

    private val event = MutableStateFlow<Event>(Event.Unit)
    fun event() = event.asStateFlow()

    val input = MutableStateFlow("")
    val movieTotalCount = MutableStateFlow(0)

    fun searchMovie() = viewModelScope.launch {
        val queries: HashMap<String, String> = hashMapOf()
        queries["title"] = input.value
        useCase.searchMovie(queries).onSuccess {
            val result = it.body()?.data?.firstOrNull()
            Log.d("Jww::", "result = ${result}")
            movieTotalCount.emit(result?.totalCount ?: 0)
            event.emit(Event.ResultMovieData(result?.result))
        }.onFailure {
            it.printStackTrace()
        }
    }


    sealed class Event {
        object Unit : Event()
        class ResultMovieData(val movieList: List<Movie.Result>?) : Event()
    }
}