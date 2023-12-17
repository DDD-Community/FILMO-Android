package com.ddd.filmo.network.model

import com.ddd.filmo.mapper.typemarker.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("title") val title: String,
    @SerialName("prodYear") val repRlsDate: String,
    @SerialName("posters") val posterUrl: String,
) : DataModel

@Serializable
data class KMAResponse(
    @SerialName("Data")
    val data: List<Datum>,
)

@Serializable
data class Datum(
    @SerialName("CollName")
    val collName: String,

    @SerialName("TotalCount")
    val totalCount: Long,

    @SerialName("Count")
    val count: Long,

    @SerialName("Result")
    val result: List<MovieResponse>,
)
