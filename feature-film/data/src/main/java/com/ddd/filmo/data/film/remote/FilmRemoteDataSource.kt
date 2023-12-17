package com.ddd.filmo.data.film.remote

import android.util.Log
import com.ddd.filmo.data.film.mapper.FilmResponseMapper
import com.ddd.filmo.data.film.model.FilmResponse
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.Scene
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

interface FilmRemoteDataSource {
    suspend fun observeFilms(
        filmsFlow: MutableStateFlow<List<Film>>,
        userId: String = GoogleUser.user.userId,
    )

    suspend fun observeFilm(
        selectedFilmFlow: MutableStateFlow<Film>,
        selectedFilmScenesFlow: MutableStateFlow<List<Scene>>,
        filmId: String,
        userId: String = "117111581200385730511"
    )

    suspend fun createFilm(name: String, color: Long, userId: String = "117111581200385730511")
    suspend fun updateFilm(name: String, color: Long, userId: String = "117111581200385730511", selectedFilmId: String)
    suspend fun deleteFilm(selectedFilmId: String, userId: String = "117111581200385730511")
    suspend fun createFilm(name: String, color: Long, userId: String = GoogleUser.user.userId)
    suspend fun updateFilm(name: String, color: Long, userId: String = GoogleUser.user.userId, selectedFilmId: String)
}

class FilmRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : FilmRemoteDataSource {
    override suspend fun observeFilms(filmsFlow: MutableStateFlow<List<Film>>, userId: String) {
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films").snapshots().map { snapshot ->
                val filmResponse = snapshot.toObjects(FilmResponse::class.java)
                Log.e("fetchFilms", "$filmResponse")
                filmResponse
            }.collect { filmResponse ->
                val films: MutableList<Film> = mutableListOf()
                filmResponse.map {
                    films.add(FilmResponseMapper.toDomain(it))
                }
                filmsFlow.value = films.toList()
            }
    }

    override suspend fun observeFilm(
        selectedFilmFlow: MutableStateFlow<Film>,
        selectedFilmScenesFlow: MutableStateFlow<List<Scene>>,
        filmId: String,
        userId: String
    ) {
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(filmId).snapshots().map { snapshot ->
                snapshot.toObject(FilmResponse::class.java)
            }.collect { filmResponse ->
                filmResponse?.let {
                    selectedFilmFlow.value = FilmResponseMapper.toDomain(filmResponse)
                    selectedFilmScenesFlow.value = filmResponse.scenes
                }
            }
    }

    override suspend fun createFilm(name: String, color: Long, userId: String) {
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document().set(
                FilmResponse(
                    caseColor = color,
                    name = name,
                    sceneCount = 0,
                    createdAt = Date(),
                ),
                SetOptions.merge(),
            ).await()
    }

    override suspend fun updateFilm(name: String, color: Long, userId: String, selectedFilmId: String) {
        val filmDocument = firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(selectedFilmId)

        filmDocument.update("name", name).await()
        filmDocument.update("caseColor", color).await()
    }

    override suspend fun deleteFilm(selectedFilmId: String, userId: String) {
        firebaseDB.collection("User")
            .document(userId)
            .collection("Films")
            .document(selectedFilmId)
            .delete().await()
    }
}
