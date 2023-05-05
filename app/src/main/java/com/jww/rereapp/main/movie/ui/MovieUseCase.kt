package com.jww.rereapp.main.movie.ui

import com.jww.rereapp.common.repository.MovieRepository
import org.koin.core.component.KoinComponent

class MovieUseCase(private val repository: MovieRepository): KoinComponent {

    suspend fun searchMovie(queries: HashMap<String, String>?) =
        kotlin.runCatching {
            repository.searchMovie(queries)
        }
}