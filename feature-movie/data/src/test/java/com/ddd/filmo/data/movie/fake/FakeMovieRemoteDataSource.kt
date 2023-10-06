package com.ddd.filmo.data.movie.fake

import com.ddd.filmo.data.movie.remote.MovieRemoteDataSource
import com.ddd.filmo.network.model.Datum
import com.ddd.filmo.network.model.KMAResponse
import com.ddd.filmo.network.model.MovieResponse

class FakeMovieRemoteDataSource : MovieRemoteDataSource {
    override suspend fun fetchMovieList(): KMAResponse {
        return fakeMovieResponse
    }

    companion object{
        val fakeMovieResponse = KMAResponse(
            data = listOf(
                Datum(
                    collName = "Sandy Trujillo",
                    totalCount = 2623,
                    count = 8584,
                    result = listOf(
                        MovieResponse(
                            title = "iusto",
                            repRlsDate = "2020",
                            posterUrl = "http://www.bing.com/search?q=vocibus",
                        ),
                    ),
                ),
            ),
        )
    }
}
