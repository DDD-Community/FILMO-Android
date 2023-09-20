package com.ddd.filmo.network

import retrofit2.http.GET
import retrofit2.http.Query

interface KmdbAPI {

    @GET("/search_json2.jsp")
    suspend fun fetchMovieList(
        @Query("ServiceKey") serviceKey: String = "",
        @Query("listCount") listCount: Int = 10,
        @Query("query") query: String? = null,
    )
}