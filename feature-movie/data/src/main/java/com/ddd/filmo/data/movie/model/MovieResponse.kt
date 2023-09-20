package com.ddd.filmo.data.movie.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("title") val title: String,
    @SerialName("repRlsDate") val repRlsDate: String,
    @SerialName("posterUrl") val posterUrl: String,
)
