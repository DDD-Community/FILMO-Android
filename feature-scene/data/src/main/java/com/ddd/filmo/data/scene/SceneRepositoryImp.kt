package com.ddd.filmo.data.scene

import com.ddd.filmo.data.scene.remote.SceneRemoteDataSource
import com.ddd.filmo.model.Scene
import com.ddd.filmo.scene.domain.repository.SceneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SceneRepositoryImp @Inject constructor(
    private val sceneRemoteDataSource: SceneRemoteDataSource,
) : SceneRepository {
    private val _selectedScene: MutableStateFlow<Scene> = MutableStateFlow(Scene())
    override val selectedScene: StateFlow<Scene> = _selectedScene

    override suspend fun selectScene(value: Scene) {
        _selectedScene.value = value
    }
    override suspend fun createScene(
        filmId: String,
        sceneText: String,
        sceneRate: Float,
        movieTitle: String,
        imageUrl: String,
    ) {
        sceneRemoteDataSource.createScene(
            filmId = filmId,
            sceneText = sceneText,
            sceneRate = sceneRate,
            movieTitle = movieTitle,
            imageUrl = imageUrl,
        )
    }
}
