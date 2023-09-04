package com.ddd.filmo.film.data.remote

import android.util.Log
import com.ddd.filmo.film.data.mapper.FilmResponseMapper
import com.ddd.filmo.film.data.model.FilmResponse
import com.ddd.filmo.model.Film
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface FilmRemoteDataSource {
    suspend fun observeFilms(filmsFlow: MutableStateFlow<List<Film>>, userId: String = "117111581200385730511")
    suspend fun createFilm(name: String, color: Long)


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

    override suspend fun createFilm(name: String, color: Long) {

    }
}
