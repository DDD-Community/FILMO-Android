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
    val allScenesList: List<Scene> // = SceneImageTest.testSceneType
    val checkedScenes = MutableStateFlow<List<Scene>>(emptyList())

    init {
        val scenes: MutableList<Scene> = mutableListOf()
        filmRepository.films.value.forEach {
            it.scenes.forEach { scene ->
                scenes.add(scene)
            }
        }
        allScenesList = scenes.toList()
    }

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

    fun deleteFilm() {
        viewModelScope.launch {
            filmRepository.deleteFilm(selectedFilm.value.documentId)
        }
    }

    fun setIsEditDialogState(value: Boolean) {
        isEditDialogState.value = value
    }

    fun toggleSceneChecked(scene: Scene) {
        val mutableCheckedScenes = checkedScenes.value.toMutableList()
        if (checkedScenes.value.contains(scene)) {
            mutableCheckedScenes.remove(scene)
        } else {
            mutableCheckedScenes.add(scene)
        }
        checkedScenes.value = mutableCheckedScenes.toList()
    }

    fun addCheckedScenes() {
        viewModelScope.launch {
            val scenes = checkedScenes.value
            scenes.forEach {
                sceneRepository.createScene(
                    filmId = selectedFilm.value.documentId,
                    sceneText = it.sceneText ?: "",
                    sceneRate = it.sceneRate ?: 0f,
                    movieTitle = it.movie?.title ?: "",
                    imageUrl = it.imageUrl,
                )
            }
        }
    }
}
