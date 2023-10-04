package com.ddd.filmo.data.movie.remote

import com.ddd.filmo.data.movie.mapper.MovieResponseMapper
import com.ddd.filmo.network.KmdbAPI
import com.ddd.filmo.network.model.MovieResponse
import javax.inject.Inject

interface MovieRemoteDataSource {
    suspend fun fetchMovieList(): MovieResponse
}

class MovieRemoteDataSourceImp @Inject constructor(
    private val kmdbAPI: KmdbAPI,
) : MovieRemoteDataSource {
    override suspend fun fetchMovieList(): MovieResponse {
        return kmdbAPI.fetchMovieList().m{
            MovieResponseMapper.toDomain(it)
        }
    }
}
