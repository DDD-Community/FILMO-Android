package com.ddd.filmo.presentation.scene.ui.read

import androidx.lifecycle.ViewModel
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.scene.domain.repository.SceneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SceneReadViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val sceneRepository: SceneRepository,
) : ViewModel() {
    val scene = sceneRepository.selectedScene
}