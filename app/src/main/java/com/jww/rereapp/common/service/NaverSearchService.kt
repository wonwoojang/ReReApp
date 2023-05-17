package com.jww.rereapp.common.service

import com.jww.rereapp.common.models.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NaverSearchService {

    @GET("search/book.json")
    suspend fun searchList(
        @Header("X-Naver-Client-Id") clientId: String? = "Qz5ieokc_Fp1jZt5aTUN",
        @Header("X-Naver-Client-Secret") clientSecret: String? = "h29TlmOXo0",
        @Query("query") query: String,
        @QueryMap queries: Map<String, Int>?,
    ): Response<Book>
}