package com.ddd.filmo.domain

import com.ddd.filmo.model.Movie

interface MovieRepository {

    suspend fun fetchMovieList(): List<Movie>
}
