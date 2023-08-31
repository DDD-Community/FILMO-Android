/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ddd.filmo.presentation.signup.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext context: Context,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun registerUser(user: User) = viewModelScope.launch {
        userRepository.saveUser(user).onStart {
            _uiState.update { it.copy(loading = true) }
        }.onCompletion {
            _uiState.update { it.copy(loading = false) }
        }.catch {
            setErrorMessage(it.message.toString())
            Log.d("LoginViewModel", "registerUser: ${it.message}")
        }.collect {
            if (it) {
                Log.d("LoginViewModel", "isUserRegistered: $it")
                _uiState.update { it.copy(isRegistered = true) }
            } else {
                setErrorMessage("이미 가입된 사용자입니다.")
            }
        }
    }

    fun setErrorMessage(error: String) {
        _uiState.update { it.copy(error = error) }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(error = "") }
    }
}

data class SignUpUiState(
    val isRegistered: Boolean = false,
    val loading: Boolean = false,
    val error: String = "",
)
