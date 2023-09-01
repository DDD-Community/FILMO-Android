package com.ddd.filmo.scene.data

import com.ddd.filmo.scene.data.remote.SceneRemoteDataSource
import com.ddd.filmo.scene.domain.repository.SceneRepository
import javax.inject.Inject

class SceneRepositoryImp @Inject constructor(
    private val sceneRemoteDataSource: SceneRemoteDataSource,
) : SceneRepository {
    
}
