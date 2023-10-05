package com.ddd.filmo.data.movie.remote

import com.ddd.filmo.network.KmdbAPI
import com.ddd.filmo.network.factory.ApiResult
import com.ddd.filmo.network.model.KMAResponse
import javax.inject.Inject

interface MovieRemoteDataSource {
    suspend fun fetchMovieList(): ApiResult<KMAResponse>
}

class MovieRemoteDataSourceImp @Inject constructor(
    private val kmdbAPI: KmdbAPI,
) : MovieRemoteDataSource {
    override suspend fun fetchMovieList(): ApiResult<KMAResponse> {
        return kmdbAPI.fetchMovieList()
    }
}
