package com.jww.rereapp.itemModel

data class MovieAdapterItem(
    val movieSeq: String?,
    val title: String?,
    val prodYear: String?,
    private val posters: String?,
) : java.io.Serializable {
    fun getPosterUrl(): String? {
        return posters?.split("|")?.firstOrNull()
    }
}