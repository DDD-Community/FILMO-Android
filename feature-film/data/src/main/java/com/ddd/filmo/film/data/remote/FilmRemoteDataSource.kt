package com.ddd.filmo.film.data.remote

import com.ddd.filmo.film.data.model.FilmResponse
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


interface FilmRemoteDataSource {
    suspend fun getScene(): FilmResponse

}

class FilmRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : FilmRemoteDataSource {
    override suspend fun getScene(): FilmResponse {
        TODO("Not yet implemented")
    }
}
