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
    }

    suspend fun searchMovie(queries: HashMap<String, String>?, startCount: Int?): Response<Movie>

    class Implement(
        private val app: App,
        private val service: MovieService
    ) : MovieRepository {
        override suspend fun searchMovie(
            queries: HashMap<String, String>?,
            startCount: Int?
        ): Response<Movie> {
            queries?.put(SERVICE_KEY, app.getString(R.string.movieApiServiceKey))
            queries?.put(DETAIL, "y")
            queries?.put(COLLECTION, "kmdb_new2")
            return service.searchMovie(queries?.toMap(), startCount)
        }
    }
}