package com.ddd.filmo.scene.data.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.SceneType
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import java.util.Date

data class SceneResponse(
    @DocumentId val documentId: String = "",
    val sceneText: String? = "",
    @Exclude val sceneType: SceneType? = SceneType.fromColor(0xff000000),
    val sceneRate: Int? = 0,
    val movie: Movie? = null,
    val createdAt: Date = Date(),
    val imageUrl: String? = null,
) : DataModel
