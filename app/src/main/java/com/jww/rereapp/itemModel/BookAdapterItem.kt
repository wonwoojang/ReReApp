package com.jww.rereapp.itemModel

import com.jww.rereapp.common.models.Book

data class BookAdapterItem(private val book: Book.Item) {

    val id
        get() = book.isbn

    val image
        get() = book.image

    val title
        get() = book.title

    val description
        get() = book.description

    val author
        get() = book.author
}