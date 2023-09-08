package com.ddd.filmo.data.film.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Scene
import com.google.firebase.firestore.DocumentId
import java.util.Date

data class FilmResponse(
    @DocumentId val documentId: String = "",
    val caseColor: Long = 0xFF9868FF,
    val name: String = "",
    val sceneCount: Int = 0,
    val isPrivate: Boolean = false,
    val createdAt: Date = Date(),
    val scenes: List<Scene> = emptyList()
) : DataModel
