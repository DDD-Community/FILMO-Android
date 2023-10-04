package com.ddd.filmo.network.model

import com.ddd.filmo.mapper.typemarker.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("title") val title: String,
    @SerialName("repRlsDate") val repRlsDate: String,
    @SerialName("posterUrl") val posterUrl: String,
) : DataModel
