package com.jww.rereapp.item_model

data class BookAdapterItem(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val author: String
) : java.io.Serializable