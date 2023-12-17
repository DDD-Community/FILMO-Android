package com.ddd.filmo.presentation.scene.ui.read

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.scene.domain.repository.SceneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SceneReadViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val sceneRepository: SceneRepository,
) : ViewModel() {
    val scene = sceneRepository.selectedScene
    val selectedFilm = filmRepository.selectedFilm

    fun deleteScene(navigateUp: () -> Unit) {
        viewModelScope.launch {
            sceneRepository.deleteScene(
                filmId = selectedFilm.value.documentId,
            )
            filmRepository
            navigateUp()
        }
    }
}