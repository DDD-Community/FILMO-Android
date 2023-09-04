package com.ddd.filmo.film.domain.repository

import com.ddd.filmo.model.Film
import kotlinx.coroutines.flow.StateFlow

interface FilmRepository {
    val films: StateFlow<List<Film>>
}
