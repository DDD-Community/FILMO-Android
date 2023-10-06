package com.ddd.filmo.data.movie

import com.ddd.filmo.core.testing.CoroutinesTestExtension
import com.ddd.filmo.data.movie.fake.FakeMovieRemoteDataSource
import com.ddd.filmo.data.movie.fake.FakeMovieRemoteDataSource.Companion.fakeMovieResponse
import com.ddd.filmo.domain.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesTestExtension::class)
class MovieRepositoryTest {

    private lateinit var mainRepository: MovieRepository

    @Nested
    @DisplayName("상품 리스트를 정삭적으로 호출되었을때")
    inner class MainSuccess {
        @BeforeEach
        fun initService() {
            mainRepository = MovieRepositoryImp(movieRemoteDataSource = FakeMovieRemoteDataSource())
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @org.junit.jupiter.api.Test
        @DisplayName("fakegoodsList를 domain 모델로 매핑하여 정상적으로 반환")
        fun verifyGetGoodsList() = runTest {
            val currentGoodsList = mainRepository.fetchMovieList().first().first()
            Assertions.assertEquals(
                currentGoodsList.title,
                fakeMovieResponse.data.first().result.first().title,
            )
        }
    }
}
