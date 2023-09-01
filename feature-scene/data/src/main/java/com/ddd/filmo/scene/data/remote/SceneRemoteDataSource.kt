package com.ddd.filmo.scene.data.remote

import com.ddd.filmo.scene.data.model.SceneResponse
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


interface SceneRemoteDataSource {
    suspend fun getScene(): SceneResponse

}

class SceneRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : SceneRemoteDataSource {
    override suspend fun getScene(): SceneResponse {
        TODO("Not yet implemented")
    }
}
