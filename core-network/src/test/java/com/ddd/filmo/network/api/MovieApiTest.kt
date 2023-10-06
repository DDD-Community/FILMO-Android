package com.ddd.filmo.network.api

import com.ddd.filmo.network.ApiAbstract
import com.ddd.filmo.network.KmdbAPI
import com.ddd.filmo.network.factory.onFailure
import com.ddd.filmo.network.factory.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MovieApiTest : ApiAbstract<KmdbAPI>() {
    private lateinit var service: KmdbAPI

    @BeforeEach
    fun initService() {
        service = createService(KmdbAPI::class.java)
    }

    @Nested
    @DisplayName("KmdbAPI API  영화 데이터가 200 떨어졌을때")
    inner class LoginDataSuccess {
        @BeforeEach
        fun setup() {
            enqueueResponse("/MovieResponse.json")
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("데이터 파싱과 응답코드가 정상적으로 반환")
        fun verifyLoggedIn() = runTest {
            val response = service.fetchMovieList()
            response.onSuccess {
                Assertions.assertEquals(
                    it.data[0].result[0].title,
                    "여호",
                )
            }.onFailure {
                Assertions.fail()
            }
        }
    }
}
