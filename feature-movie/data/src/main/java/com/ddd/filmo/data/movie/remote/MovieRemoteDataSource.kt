package com.ddd.filmo.data.movie.remote

import com.ddd.filmo.network.KmdbAPI
import com.ddd.filmo.network.model.MovieResponse
import javax.inject.Inject

interface MovieRemoteDataSource {
    fun fetchMovieList(): MovieResponse {
    }
}

class MovieRemoteDataSourceImp @Inject constructor(
    private val kmdbAPI: KmdbAPI,
) : MovieRemoteDataSource
