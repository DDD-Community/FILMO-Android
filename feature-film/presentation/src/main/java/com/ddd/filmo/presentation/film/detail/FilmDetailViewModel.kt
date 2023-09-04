package com.ddd.filmo.presentation.film.detail

import androidx.lifecycle.ViewModel
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
) : ViewModel() {
    val selectedFilm = filmRepository.selectedFilm
    val selectedFilmScenes = filmRepository.selectedFilmScenes
}