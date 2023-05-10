package com.jww.rereapp.common.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Data")
    val data: List<Data>? = null,
    @SerializedName("KmaQuery")
    val kmaQuery: String?,
    @SerializedName("Query")
    val query: String?,
    @SerializedName("TotalCount")
    val totalCount: Int? = 0
) {
    data class Data(
        @SerializedName("CollName")
        val collName: String,
        @SerializedName("Count")
        val count: Int? = 0,
        @SerializedName("Result")
        val result: List<Result>? = null,
        @SerializedName("TotalCount")
        val totalCount: Int? = 0
    )

    data class Result(
        val DOCID: String?,
        val actors: Actors?,
        val company: String?,
        val directors: Directors?,
        val genre: String?,
        val kmdbUrl: String?,
        val movieId: String?,
        val movieSeq: String?,
        val nation: String?,
        val plots: Plots?,
        val prodYear: String?,
        val rating: String?,
        val posters: String?,
        val runtime: String?,
        val title: String?,
        val titleEng: String?,
        val titleEtc: String?,
        val titleOrg: String?
    )

    data class Actors(
        val actor: List<Actor>? = null
    ) {
        data class Actor(
            val actorEnNm: String?,
            val actorId: String?,
            val actorNm: String?
        )
    }

    data class Directors(
        val director: List<Director>? = null
    ) {
        data class Director(
            val directorEnNm: String?,
            val directorId: String?,
            val directorNm: String?
        )
    }

    data class Plots(
        val plot: List<Plot>? = null
    ) {
        data class Plot(
            val plotLang: String?,
            val plotText: String?
        )
    }
}