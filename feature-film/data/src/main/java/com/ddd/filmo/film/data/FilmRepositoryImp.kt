package com.ddd.filmo.film.data

import com.ddd.filmo.film.data.remote.FilmRemoteDataSource
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.model.Film
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmRepositoryImp @Inject constructor(
    private val filmRemoteDataSource: FilmRemoteDataSource,
) : FilmRepository {
    val _films: MutableStateFlow<List<Film>> = MutableStateFlow(emptyList())
    override val films: StateFlow<List<Film>> = _films

    override suspend fun createFilm(name: String, color: Long) {
        filmRemoteDataSource.createFilm(name, color)
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            filmRemoteDataSource.observeFilms(_films)
        }
    }
}
