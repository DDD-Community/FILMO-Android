package com.ddd.filmo.login.domain.repository

import com.ddd.filmo.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val currentUser: StateFlow<User?>

    suspend fun fetchUser()

    suspend fun isExitUser(userId: String): Flow<Boolean>

    fun saveUser(user: User): Flow<Boolean>

    fun deleteUser(): Boolean
}
