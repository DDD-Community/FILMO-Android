package com.ddd.filmo.data.movie

import com.ddd.filmo.data.movie.remote.MovieRemoteDataSource
import com.ddd.filmo.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {
    override suspend fun fetchMovieList() = movieRemoteDataSource.fetchMovieList()
}
