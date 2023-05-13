package com.jww.rereapp.common.repository

import com.jww.rereapp.App
import com.jww.rereapp.R
import com.jww.rereapp.common.models.Movie
import com.jww.rereapp.common.service.MovieService
import retrofit2.Response

interface MovieRepository {
    companion object {
        const val SERVICE_KEY = "ServiceKey"
        const val DETAIL = "detail"
        const val COLLECTION = "collection"
        const val TITLE = "title"
    }

    suspend fun searchMovie(searchWord: String, startCount: Int): Response<Movie>

    class Implement(
        private val app: App,
        private val service: MovieService
    ) : MovieRepository {
        override suspend fun searchMovie(
            searchWord: String,
            startCount: Int
        ): Response<Movie> {
            val queries = HashMap<String, String>()
            queries.apply {
                put(SERVICE_KEY, app.getString(R.string.movieApiServiceKey))
                put(DETAIL, "y")
                put(COLLECTION, "kmdb_new2")
                put(TITLE, searchWord)
            }
            return service.searchMovie(queries.toMap(), startCount)
        }
    }
}