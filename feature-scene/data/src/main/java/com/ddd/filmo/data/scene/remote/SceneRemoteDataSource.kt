package com.ddd.filmo.data.scene.remote

import android.util.Log
import com.ddd.filmo.data.scene.model.SceneResponse
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
        userId: String = "117111581200385730511",
        filmId: String,
        sceneText: String,
        sceneRate: Float,
        movieTitle: String,
        imageUrl: String,
    )
    suspend fun deleteScene(
        userId: String = "117111581200385730511",
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

    override suspend fun deleteScene(
        userId: String,
        filmId: String,
        scene: Scene
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
                    Log.e("deleteScene", it.toString())
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
