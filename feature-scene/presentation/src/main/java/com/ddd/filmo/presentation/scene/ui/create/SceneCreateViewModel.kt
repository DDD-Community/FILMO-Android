package com.ddd.filmo.presentation.scene.ui.create

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.Movie
import com.ddd.filmo.scene.domain.repository.SceneRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SceneCreateViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val sceneRepository: SceneRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val films = filmRepository.films
    val sceneText = MutableStateFlow("")
    val movieTitle = MutableStateFlow(savedStateHandle.get<String>("movieTitle") ?: "")
    val rating = MutableStateFlow(2.5f)
    val selectedFilm = MutableStateFlow(films.value.first())
    val selectedPhoto: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val isUploading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isCanCreateScene = MutableStateFlow(false)

    fun createScene(movie: Movie?, navigateToSth: () -> Unit) {
        viewModelScope.launch {
            isUploading.value = true
            var imageUrl = ""
            val selectedUriValue = selectedPhoto.value
            if (selectedUriValue != null) {
                imageUrl = uploadImageAndGetUrl(selectedUriValue)
            }
            sceneRepository.createScene(
                filmId = selectedFilm.value.documentId,
                sceneText = sceneText.value,
                sceneRate = rating.value,
                movieTitle = movie?.title ?: "",
                imageUrl = imageUrl,
            )
            navigateToSth()
        }
    }

    suspend fun uploadImageAndGetUrl(uri: Uri): String {
        val sdf = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val fileName = sdf.format(Date())
        val path = "${userRepository.currentUser.value?.userId}/$fileName"
        val fileRef: StorageReference =
            FirebaseStorage.getInstance().reference.child(path)
        fileRef.putFile(uri).await()
        return path
    }

    fun setSceneText(value: String) {
        sceneText.value = value
        checkSceneCreatable()
    }

    fun setMovieTitle(value: String) {
        movieTitle.value = value
        checkSceneCreatable()
    }

    private fun checkSceneCreatable() {
        isCanCreateScene.value = !(sceneText.value.isEmpty() || movieTitle.value.isEmpty())
    }

    fun setRating(value: Float) {
        rating.value = value
    }

    fun setSelectedUri(value: Uri?) {
        selectedPhoto.value = value
    }

    fun selectFilm(film: Film) {
        selectedFilm.value = film
    }
}
