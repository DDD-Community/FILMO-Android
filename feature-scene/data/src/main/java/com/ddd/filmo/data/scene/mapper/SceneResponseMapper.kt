package com.ddd.filmo.data.scene.mapper

import com.ddd.filmo.data.scene.model.SceneResponse
import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.Scene

object SceneResponseMapper : DomainMapper<SceneResponse, Scene> {
    override fun toDomain(data: SceneResponse): Scene {
        return Scene(
            documentId = data.documentId,
            sceneText = data.sceneText,
            // sceneType = data.sceneType,
            sceneRate = data.sceneRate,
            movie = data.movie,
            createdAt = data.createdAt,
            imageUrl = data.imageUrl ?: "",
        )
    }
}