package com.ddd.filmo.presentation.scene.ui.create

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.Scene
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SceneCreateViewModel  @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
) : ViewModel() {
    val scene = MutableStateFlow(Scene())
    val films = filmRepository.films.value
    val sceneText = MutableStateFlow("")
    val movieTitle = MutableStateFlow("")
    val rating = MutableStateFlow(2.5f)
    val selectedFilm = MutableStateFlow(filmRepository.films.value.first())
    val selectedUri: MutableStateFlow<Uri?> = MutableStateFlow(null)

    fun setSceneText(value: String) {
        sceneText.value = value
    }

    fun setMovieTitle(value: String) {
        movieTitle.value = value
    }
    
    fun setRating(value: Float) {
        rating.value = value
    }

    fun setSelectedUri(value: Uri?) {
        selectedUri.value = value
    }

    fun selectFilm (film: Film) {
        selectedFilm.value = film
    }
}