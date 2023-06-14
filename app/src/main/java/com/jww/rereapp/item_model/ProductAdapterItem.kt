package com.jww.rereapp.item_model

sealed interface ProductAdapterItem : java.io.Serializable {

    companion object {
        const val BOOK = 1
        const val MOVIE = 2
    }

    val viewType: Int

    data class BookAdapterItem(
        val id: String,
        val image: String,
        val title: String,
        val description: String,
        val author: String
    ) : ProductAdapterItem, java.io.Serializable {
        override val viewType: Int
            get() = BOOK
    }

    data class MovieAdapterItem(
        val movieSeq: String?,
        val title: String?,
        val prodYear: String?,
        private val posters: String?,
    ) : ProductAdapterItem, java.io.Serializable {
        fun getPosterUrl(): String? {
            return posters?.split("|")?.firstOrNull()
        }

        override val viewType: Int
            get() = MOVIE
    }
}