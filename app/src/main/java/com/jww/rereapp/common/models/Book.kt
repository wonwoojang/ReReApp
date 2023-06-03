package com.jww.rereapp.common.models

data class Book(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
) {
    data class Item(
        val author: String,
        val description: String,
        val discount: String,
        val image: String,
        val isbn: String,
        val link: String,
        val pubdate: String,
        val publisher: String,
        val title: String
    )
}