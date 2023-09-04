package com.ddd.filmo.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
) : ViewModel() {
    val user: MutableStateFlow<User?> = MutableStateFlow(userRepository.currentUser.value)
    val films: StateFlow<List<Film>> = filmRepository.films

    fun createFilm(name: String, color: Long) {

    }
}
