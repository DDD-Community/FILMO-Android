package com.ddd.filmo.scene.domain.repository

import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.Scene
import kotlinx.coroutines.flow.StateFlow

interface SceneRepository {
    val selectedScene: StateFlow<Scene>
    suspend fun selectScene(value: Scene)
    suspend fun createScene(
        filmId: String,
        sceneText: String,
        sceneRate: Float,
        movieTitle: String,
        imageUrl: String,
        movie: Movie? = null,
    )

    suspend fun deleteScene(filmId: String)
}
