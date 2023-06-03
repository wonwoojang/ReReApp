package com.jww.rereapp.main.book

import com.jww.rereapp.common.repository.NaverSearchRepository
import org.koin.core.component.KoinComponent

class BookUseCase(private val repository: NaverSearchRepository) : KoinComponent {
    suspend fun searchList(searchWord: String?, currentCount: Int? = 0, displayCount: Int? = 10) =

        kotlin.runCatching {
            repository.searchList(searchWord!!, currentCount!!, displayCount!!)
        }
}