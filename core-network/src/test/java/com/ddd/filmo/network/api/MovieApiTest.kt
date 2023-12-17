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
                    " 데어데블 vs.  !HS 스파이더맨 !HE ",
                )
                Assertions.assertEquals(
                    it.data[0].result[0].repRlsDate,
                    "2002",
                )
            }.onFailure {
                Assertions.fail()
            }
        }
    }

    @Test
    fun `replaceParsing이 제대로 이루어지는가?`() {
        val title = " 데어데블 vs.  !HS 스파이더맨 !HE "
        val replace = title.replaceRange(" !HS ", "")
        Assertions.assertEquals(replace, " 데어데블 vs.  스파이더맨 !HE ")
    }
}
