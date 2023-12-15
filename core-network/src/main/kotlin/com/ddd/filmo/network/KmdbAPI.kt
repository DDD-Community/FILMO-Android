package com.ddd.filmo.network

import com.ddd.filmo.network.factory.ApiResult
import com.ddd.filmo.network.model.KMAResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KmdbAPI {

    @GET("/openapi-data2/wisenut/search_api/search_json2.jsp")
    suspend fun fetchMovieList(
        @Query("ServiceKey") serviceKey: String = "H6GU9QT50174J6BI1QWP",
        @Query("listCount") listCount: Int = 10,
        @Query("title") query: String? = null,
        @Query("detail") detail: String = "N",
        @Query("collection") collection: String = "kmdb_new2",
    ): ApiResult<KMAResponse>
}
