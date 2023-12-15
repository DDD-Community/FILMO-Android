package com.ddd.filmo.presentation.film.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.Scene
import com.ddd.filmo.scene.domain.repository.SceneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val sceneRepository: SceneRepository,
) : ViewModel() {
    val selectedFilm = filmRepository.selectedFilm
    val selectedFilmScenes = filmRepository.selectedFilmScenes
    val isEditDialogState = MutableStateFlow(false)

    fun selectScene(scene: Scene) {
        viewModelScope.launch {
            sceneRepository.selectScene(scene)
        }
    }

    fun updateFilm(name: String, color: Long) {
        viewModelScope.launch {
            filmRepository.updateFilm(name, color, selectedFilm.value.documentId)
        }
        isEditDialogState.value = false
    }

    fun setIsEditDialogState(value: Boolean) {
        isEditDialogState.value = value
    }
}