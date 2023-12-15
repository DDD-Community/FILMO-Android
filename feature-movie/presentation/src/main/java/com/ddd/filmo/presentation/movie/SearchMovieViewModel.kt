package com.ddd.filmo.presentation.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.domain.MovieRepository
import com.ddd.filmo.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.None)
    val uiState = _uiState.asStateFlow()

    fun searchMovie(query: String? = null) = viewModelScope.launch {
        movieRepository.fetchMovieList(query = query).onStart {
            _uiState.update { SearchUiState.Loading }
        }.catch { error ->
            Log.d("SearchMovieViewModel", "error: $error")
            _uiState.update { SearchUiState.Error(throwable = error) }
        }.collect { movieList ->
            if (movieList.isEmpty()) {
                _uiState.update { SearchUiState.Empty }
            } else {
                _uiState.update { SearchUiState.Success(movieList) }
            }
        }
    }
}

sealed interface SearchUiState {
    data object None : SearchUiState
    data object Empty : SearchUiState
    data object Loading : SearchUiState
    data class Success(val movieList: List<Movie>) : SearchUiState
    data class Error(val throwable: Throwable) : SearchUiState
}
