package com.ddd.filmo.scene.domain.repository

import com.ddd.filmo.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SceneRepository {
    suspend fun createScene(filmId: String, sceneText: String, sceneRate: Float, movieTitle: String, imageUrl: String, )
}
