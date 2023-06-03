package com.jww.rereapp.common.repository

import com.jww.rereapp.App
import com.jww.rereapp.common.models.Book
import com.jww.rereapp.common.service.NaverSearchService
import retrofit2.Response

interface NaverSearchRepository {
    companion object {
        const val START = "start"
        const val DISPLAY = "display"
    }

    suspend fun searchList(searchWord: String, currentCount: Int, displayCount: Int): Response<Book>

    class Implement(private val app: App, private val service: NaverSearchService) :
        NaverSearchRepository {
        override suspend fun searchList(
            searchWord: String,
            currentCount: Int,
            displayCount: Int
        ): Response<Book> {

            val queries = HashMap<String, Int>().apply {
                put(START, currentCount)
                put(DISPLAY, displayCount)
            }
            return service.searchList(
                "Qz5ieokc_Fp1jZt5aTUN",
                "h29TlmOXo0",
                searchWord,
                queries
            )
        }
    }
}