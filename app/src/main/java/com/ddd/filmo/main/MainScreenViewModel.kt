package com.ddd.filmo.main

import androidx.lifecycle.ViewModel
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user: MutableStateFlow<User?> = MutableStateFlow(userRepository.currentUser.value)
}
