package com.ddd.filmo.film.data.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Scene
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
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
