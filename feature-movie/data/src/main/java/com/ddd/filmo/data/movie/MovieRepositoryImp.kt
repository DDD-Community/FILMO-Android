package com.ddd.filmo.data.movie

import com.ddd.filmo.data.movie.mapper.MovieResponseMapper
import com.ddd.filmo.data.movie.remote.MovieRemoteDataSource
import com.ddd.filmo.domain.MovieRepository
import com.ddd.filmo.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {
    override suspend fun fetchMovieList(query: String?, listSize: Int): Flow<List<Movie>> = flow {
        val result = query?.let {
            movieRemoteDataSource.fetchMovieList(listSize, it).data.flatMap {
                it.result.map { MovieResponseMapper.toDomain(it) }
            }
        }
        emit(result ?: listOf())
    }
}
