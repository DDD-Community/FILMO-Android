package com.ddd.filmo.presentation.mypage.ui

import androidx.lifecycle.ViewModel
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user: MutableStateFlow<User?> = MutableStateFlow(userRepository.currentUser.value)

}
