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

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val loginRepository: LoginRepository
) : ViewModel() {

//    val uiState: StateFlow<LoginUiState> = loginRepository
//        .logins.map<List<String>, LoginUiState> { Success(data = it) }
//        .catch { emit(Error(it)) }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)
//
//    fun addLogin(name: String) {
//        viewModelScope.launch {
//            loginRepository.add(name)
//        }
//    }
}

sealed interface LoginUiState {
    object Loading : LoginUiState
    data class Error(val throwable: Throwable) : LoginUiState
    data class Success(val data: List<String>) : LoginUiState
}
