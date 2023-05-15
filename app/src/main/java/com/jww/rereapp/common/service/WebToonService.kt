package com.jww.rereapp.common.service

import com.jww.rereapp.common.models.WebToon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WebToonService {

    @GET("search/rgDtaMasterList")
    suspend fun searchList(
        @QueryMap queries: Map<String, String>?
    ): Response<WebToon>
}