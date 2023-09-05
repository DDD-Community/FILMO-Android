package com.ddd.filmo.scene.data

import com.ddd.filmo.scene.data.remote.SceneRemoteDataSource
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
