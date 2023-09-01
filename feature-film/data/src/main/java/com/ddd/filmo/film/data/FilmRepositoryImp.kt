package com.ddd.filmo.film.data

import com.ddd.filmo.film.data.remote.FilmRemoteDataSource
import com.ddd.filmo.film.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(
    private val filmRemoteDataSource: FilmRemoteDataSource,
) : FilmRepository {
    
}
