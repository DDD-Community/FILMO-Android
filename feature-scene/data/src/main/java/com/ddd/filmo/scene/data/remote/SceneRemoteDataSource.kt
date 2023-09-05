package com.ddd.filmo.scene.data.remote

import com.ddd.filmo.model.Movie
import com.ddd.filmo.scene.data.model.SceneResponse
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject


interface SceneRemoteDataSource {
    suspend fun getScene(): SceneResponse
    suspend fun createScene(
        userId: String = "117111581200385730511",
        filmId: String,sceneText: String, sceneRate: Float, movieTitle: String, imageUrl: String
    )

}

class SceneRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : SceneRemoteDataSource {
    override suspend fun getScene(): SceneResponse {
        TODO("Not yet implemented")
    }

    override suspend fun createScene(
        userId: String,
        filmId: String,
        sceneText: String,
        sceneRate: Float,
        movieTitle: String,
        imageUrl: String,
    ) {
        val scene = SceneResponse(
            sceneText = sceneText,
            sceneRate = sceneRate,
            movie = Movie(movieTitle, 0, ""),
            createdAt = Date()
        )
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(filmId)
            .update("scenes", FieldValue.arrayUnion(scene))
            .await()

    }
}
