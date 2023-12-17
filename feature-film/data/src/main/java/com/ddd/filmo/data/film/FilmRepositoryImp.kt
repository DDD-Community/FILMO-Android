package com.ddd.filmo.data.film

import com.ddd.filmo.data.film.remote.FilmRemoteDataSource
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.Scene
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

    val _selectedFilm: MutableStateFlow<Film> = MutableStateFlow(Film())
    override val selectedFilm: StateFlow<Film> = _selectedFilm

    val _selectedFilmScenes: MutableStateFlow<List<Scene>> = MutableStateFlow(emptyList())
    override val selectedFilmScenes: StateFlow<List<Scene>> = _selectedFilmScenes

    override fun setSelectedFilm(film: Film) {
        _selectedFilm.value = film
        _selectedFilmScenes.value = film.scenes
    }

    override suspend fun createFilm(name: String, color: Long) {
        filmRemoteDataSource.createFilm(name, color)
    }

    override suspend fun updateFilm(name: String, color: Long, selectedFilmId: String) {
        filmRemoteDataSource.updateFilm(name, color, selectedFilmId = selectedFilmId, userId = GoogleUser.user.userId)
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            filmRemoteDataSource.observeFilms(_films)
        }
    }
}
