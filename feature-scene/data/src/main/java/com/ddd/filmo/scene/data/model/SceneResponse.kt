package com.ddd.filmo.scene.data.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.SceneType
import java.util.Date

data class SceneResponse(
    val sceneId: String? = "",
    val sceneText: String? = "",
    val sceneType: SceneType? = SceneType.fromColor(0x00000000),
    val sceneRate: Int? = 0,
    val movie: Movie? = null,
    val createdAt: Date = Date(),
) : DataModel
