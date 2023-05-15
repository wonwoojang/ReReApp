package com.jww.rereapp.common.repository

import com.jww.rereapp.App
import com.jww.rereapp.common.models.WebToon
import com.jww.rereapp.common.service.WebToonService
import retrofit2.Response

interface WebToonRepository {
    companion object {
        const val PRV_KEY = "prvKey"
        const val LIST_SE_CD = "listSeCd"
        const val PAGE_NO = "pageNo"
        const val VIEW_ITEM_CNT = "viewItemCnt"
        const val TITLE = "title"
    }

    suspend fun searchList(searchWord: String, pagingNo: Int): Response<WebToon>

    class Implement(private val app: App, private val service: WebToonService) : WebToonRepository {
        override suspend fun searchList(searchWord: String, pagingNo: Int): Response<WebToon> {

            val queries = HashMap<String, String>().apply {
                put(PRV_KEY, "fdda34ff8efd9a5ff6177432fcef74cc")
                put(LIST_SE_CD, "1")
                put(PAGE_NO, pagingNo.toString())
                put(VIEW_ITEM_CNT, "10")
                put(TITLE, searchWord)
            }
            return service.searchList(queries)
        }
    }
}