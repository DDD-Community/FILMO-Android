package com.ddd.filmo.data.scene.remote

import com.ddd.filmo.data.scene.model.SceneResponse
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.Movie
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

interface SceneRemoteDataSource {
    suspend fun getScene(): SceneResponse
    suspend fun createScene(
        userId: String = GoogleUser.user.userId,
        filmId: String,
        sceneText: String,
        sceneRate: Float,
        movieTitle: String,
        imageUrl: String,
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
            createdAt = Date(),
            imageUrl = imageUrl,
        )
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(filmId)
            .update("scenes", FieldValue.arrayUnion(scene))
            .await()
    }
}
