package com.ddd.filmo.presentation.mypage.ui

import androidx.lifecycle.ViewModel
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
) : ViewModel() {
    val user: MutableStateFlow<User?> = MutableStateFlow(userRepository.currentUser.value)
    val films = filmRepository.films.value
    val filmCount = films.size
    val sceneCount: Int

    init {
        var tempSceneCount = 0
        films.forEach { tempSceneCount += it.scenes.size }
        sceneCount = tempSceneCount
    }
}
