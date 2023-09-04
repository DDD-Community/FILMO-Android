package com.ddd.filmo.film.domain.repository

import com.ddd.filmo.model.Film
import kotlinx.coroutines.flow.StateFlow

interface FilmRepository {
    val films: StateFlow<List<Film>>
    val selectedFilm: StateFlow<Film>
    suspend fun createFilm(name: String, color: Long)
    fun setSelectedFilm(film: Film)
}
