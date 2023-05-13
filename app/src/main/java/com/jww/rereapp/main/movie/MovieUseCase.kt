package com.jww.rereapp.main.movie

import com.jww.rereapp.common.repository.MovieRepository
import org.koin.core.component.KoinComponent

class MovieUseCase(private val repository: MovieRepository) : KoinComponent {
    suspend fun searchMoviePaging(searchWord: String?, startCount: Int?) =
        kotlin.runCatching {
            repository.searchMovie(searchWord!!, startCount!!)
        }
}