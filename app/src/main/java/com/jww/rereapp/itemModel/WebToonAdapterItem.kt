package com.jww.rereapp.itemModel

import com.jww.rereapp.common.models.WebToon

data class WebToonAdapterItem(private val webToon: WebToon.Item) {

    val id
        get() = webToon.mastrId

    val imageUri
        get() = webToon.imageDownloadUrl

    val title
        get() = webToon.title

    val outLine
        get() = webToon.outline

    val age
        get() = webToon.ageGradCdNm
}