package com.ddd.filmo.network.api

import com.ddd.filmo.network.ApiAbstract
import com.ddd.filmo.network.KmdbAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LoginApiTest : ApiAbstract<KmdbAPI>() {
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
            Assertions.assertTrue(response.code() == 200)
            val parsingData = response.executeHandle()

            Assertions.assertEquals(
                parsingData,
                LoginResponse(accessToken = "오빤 강남스따일"),
            )
        }
    }
}
