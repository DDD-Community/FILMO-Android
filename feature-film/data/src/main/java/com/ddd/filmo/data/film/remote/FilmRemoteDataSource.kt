package com.ddd.filmo.data.film.remote

import android.util.Log
import com.ddd.filmo.data.film.mapper.FilmResponseMapper
import com.ddd.filmo.data.film.model.FilmResponse
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.GoogleUser
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
}
