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

package com.ddd.filmo.presentation.login.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    @ApplicationContext context: Context,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        checkLastSignedInUser(context)
    }

    private fun checkLastSignedInUser(context: Context) {
        val gsa = GoogleSignIn.getLastSignedInAccount(context)

        if (gsa != null) {
            Log.d("LoginViewModel", "checkLastSignedInUser: ${gsa.id}")

            val user = User(
                userId = gsa.id!!,
                name = gsa.displayName!!,
                email = gsa.email!!,
            )

            isUserRegistered(user)
        }
    }

    fun isUserRegistered(user: User) = viewModelScope.launch {
        userRepository.isExitUser(user.userId).catch {
            _uiState.update { it.copy(isFirstLogin = true) }
        }.collect {
            GoogleUser.user = user
            if (it) {
                Log.d("LoginViewModel", "isUserRegistered: $it")
                userRepository.fetchUser()
                _uiState.update { it.copy(isLogin = true) }
            } else {
                //todo GoogleUser.user = user 가 remove..?
                _uiState.update { it.copy(isFirstLogin = true) }
            }
        }
    }

    fun setErrorMessage(error: String) {
        Log.d("LoginViewModel", "isUserRegistered: $error")
        _uiState.update { it.copy(error = error) }
    }

    fun clearErrorMessage() {
        _uiState.update { it.copy(error = "") }
    }

    fun clearLoginState() {
        _uiState.update { it.copy(isLogin = false) }
    }

    fun clearFirstLoginState() {
        _uiState.update { it.copy(isFirstLogin = false) }
    }
}

data class LoginUiState(
    val isLogin: Boolean = false,
    val isFirstLogin: Boolean = false,
    val loading: Boolean = false,
    val error: String = "",
)
