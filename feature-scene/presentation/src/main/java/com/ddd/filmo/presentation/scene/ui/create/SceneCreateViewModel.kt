package com.ddd.filmo.presentation.scene.ui.create

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.film.domain.repository.FilmRepository
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.Film
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
class SceneCreateViewModel  @Inject constructor(
    private val userRepository: UserRepository,
    private val filmRepository: FilmRepository,
    private val sceneRepository: SceneRepository
) : ViewModel() {
    val films = filmRepository.films.value
    val sceneText = MutableStateFlow("")
    val movieTitle = MutableStateFlow("")
    val rating = MutableStateFlow(2.5f)
    val selectedFilm = MutableStateFlow(filmRepository.films.value.first())
    val selectedUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val isUploading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun createScene(navigateToSth: () -> Unit) {
        viewModelScope.launch {
            isUploading.value = true
            var imageUrl = ""
            val selectedUriValue = selectedUri.value
            if (selectedUriValue != null) {
                imageUrl = uploadImageAndGetUrl(selectedUriValue)
            }
            sceneRepository.createScene(
                filmId = selectedFilm.value.documentId,
                sceneText = sceneText.value,
                sceneRate = rating.value,
                movieTitle = movieTitle.value,
                imageUrl = imageUrl
            )
            navigateToSth()
        }
    }

    suspend fun uploadImageAndGetUrl(uri: Uri): String {
        val sdf = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val fileName = sdf.format(Date())
        val path = "${userRepository.currentUser.value?.userId}/${fileName}"
        val fileRef: StorageReference =
            FirebaseStorage.getInstance().reference.child(path)
        fileRef.putFile(uri).await()
        return path
    }

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