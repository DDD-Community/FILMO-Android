package com.ddd.filmo.domain

import com.ddd.filmo.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchMovieList(query: String?, listSize: Int = 10): Flow<List<Movie>>
}
