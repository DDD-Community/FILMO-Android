package com.ddd.filmo.core.module

import com.ddd.filmo.data.film.remote.FilmRemoteDataSource
import com.ddd.filmo.data.film.remote.FilmRemoteDataSourceImpl
import com.ddd.filmo.data.login.remote.UserRemoteDataSource
import com.ddd.filmo.data.login.remote.UserRemoteDataSourceImpl
import com.ddd.filmo.data.movie.remote.MovieRemoteDataSource
import com.ddd.filmo.data.movie.remote.MovieRemoteDataSourceImp
import com.ddd.filmo.data.scene.remote.SceneRemoteDataSource
import com.ddd.filmo.data.scene.remote.SceneRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// / TODO: 모듈을 하나 더 만들어서 여기서 주입을 시킬지.. 아니면 각 데이터 모듈에서 해야할지 고민중
@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    @Singleton
    fun bindUserRemoteSource(userRemoteDataSource: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    fun bindFilmRemoteSource(filmRemoteDataSource: FilmRemoteDataSourceImpl): FilmRemoteDataSource

    @Binds
    @Singleton
    fun bindSceneRemoteSource(sceneRemoteDataSource: SceneRemoteDataSourceImpl): SceneRemoteDataSource

    @Binds
    @Singleton
    fun bindMovieRemoteSource(movieRemoteDataSourceImp: MovieRemoteDataSourceImp): MovieRemoteDataSource
}
