package com.ddd.filmo.data.scene

import com.ddd.filmo.data.scene.remote.SceneRemoteDataSource
import com.ddd.filmo.scene.domain.repository.SceneRepository
import javax.inject.Inject

class SceneRepositoryImp @Inject constructor(
    private val sceneRemoteDataSource: SceneRemoteDataSource,
) : SceneRepository {

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
