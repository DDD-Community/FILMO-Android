package com.ddd.filmo.core.module

import com.ddd.filmo.data.film.FilmRepositoryImp
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.data.login.UserRepositoryImp
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.data.scene.SceneRepositoryImp
import com.ddd.filmo.scene.domain.repository.SceneRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// / TODO: 모듈을 하나 더 만들어서 여기서 주입을 시킬지.. 아니면 각 데이터 모듈에서 해야할지 고민중
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImp: UserRepositoryImp): UserRepository

    @Binds
    @Singleton
    fun bindFilmRepository(filmRepositoryImp: FilmRepositoryImp): FilmRepository

    @Binds
    @Singleton
    fun bindSceneRepository(sceneRepositoryImp: SceneRepositoryImp): SceneRepository
}
