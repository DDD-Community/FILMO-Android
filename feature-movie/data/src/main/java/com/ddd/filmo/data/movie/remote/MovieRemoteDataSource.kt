package com.ddd.filmo.data.movie.remote

import com.ddd.filmo.network.KmdbAPI
import com.ddd.filmo.network.factory.handlingNetwork
import com.ddd.filmo.network.model.KMAResponse
import javax.inject.Inject

interface MovieRemoteDataSource {
    suspend fun fetchMovieList(listSize: Int, query: String): KMAResponse
}

class MovieRemoteDataSourceImp @Inject constructor(
    private val kmdbAPI: KmdbAPI,
) : MovieRemoteDataSource {
    override suspend fun fetchMovieList(listSize: Int, query: String): KMAResponse {
        return handlingNetwork { kmdbAPI.fetchMovieList(listCount = listSize, query = query) }
    }
}
