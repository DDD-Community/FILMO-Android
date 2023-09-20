package com.ddd.filmo.domain

interface MovieRepository {

    suspend fun fetchMovieList()
}
