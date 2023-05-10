package com.jww.rereapp.common.service

import com.jww.rereapp.common.models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {

    @GET("search_json2.jsp")
    suspend fun searchMovie(
        @QueryMap queries: Map<String, String>?,
        @Query("startCount") startCount: Int? = 0
    ): Response<Movie>
}