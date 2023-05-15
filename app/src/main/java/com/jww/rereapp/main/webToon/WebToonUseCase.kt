package com.jww.rereapp.main.webToon

import com.jww.rereapp.common.repository.WebToonRepository
import org.koin.core.component.KoinComponent

class WebToonUseCase(private val repository: WebToonRepository) : KoinComponent {

    suspend fun searchList(searchWord: String?, pagingNo: Int? = 0) = kotlin.runCatching {
        repository.searchList(searchWord!!, pagingNo!!)
    }
}