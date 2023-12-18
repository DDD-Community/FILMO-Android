package com.ddd.filmo.data.scene.remote

import com.ddd.filmo.data.scene.model.SceneResponse
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.Scene
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
        movie: Movie?,
    )
    suspend fun deleteScene(
        userId: String = GoogleUser.user.userId,
        filmId: String,
        scene: Scene
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
        movie: Movie?,
    ) {
        val scene = SceneResponse(
            sceneText = sceneText,
            sceneRate = sceneRate,
            movie = movie,
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

    override suspend fun deleteScene(
        userId: String,
        filmId: String,
        scene: Scene,
    ) {
        val filmRef = firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(filmId)

        val scenes = filmRef.get().await().toObject(Film::class.java)?.scenes

        if (scenes != null) {
            var willDeleteIndex: Int? = null
            scenes.forEachIndexed { index, it ->
                if (scene.createdAt.time == it.createdAt.time) {
                    willDeleteIndex = index
                }
            }

            willDeleteIndex?.let {
                val mutableScenes = scenes.toMutableList()
                mutableScenes.removeAt(it)
                filmRef.update("scenes", mutableScenes.toList()).await()
            }
        }
    }
}
