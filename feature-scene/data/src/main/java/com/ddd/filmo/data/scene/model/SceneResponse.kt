package com.ddd.filmo.data.scene.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Movie
import com.google.firebase.firestore.DocumentId
import java.util.Date

data class SceneResponse(
    @DocumentId val documentId: String = "",
    val sceneText: String? = "",
    //val sceneType: SceneType? = SceneType.fromColor(0xff000000),
    val sceneRate: Float? = 0f,
    val movie: Movie? = null,
    val createdAt: Date = Date(),
    val imageUrl: String? = null,
) : DataModel