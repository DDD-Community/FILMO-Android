package com.ddd.filmo.scene.data.mapper

import com.ddd.filmo.scene.data.model.SceneResponse
import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.Scene

object SceneResponseMapper : DomainMapper<SceneResponse, Scene> {
    override fun toDomain(data: SceneResponse): Scene {
        return Scene(
            sceneId = data.sceneId,
            sceneText = data.sceneText,
            sceneType = data.sceneType,
            sceneRate = data.sceneRate,
            movie = data.movie,
            createdAt = data.createdAt,
        )
    }
}
