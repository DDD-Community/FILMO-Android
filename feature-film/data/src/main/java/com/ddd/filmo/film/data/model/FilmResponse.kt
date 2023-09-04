package com.ddd.filmo.film.data.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.google.firebase.firestore.DocumentId
import java.util.Date

data class FilmResponse(
    @DocumentId val documentId: String = "",
    val caseColor: Long = 0xFF9868FF,
    val name: String = "",
    val sceneCount: Int = 0,
    val isPrivate: Boolean = false,
    val createdAt: Date = Date(),
) : DataModel
