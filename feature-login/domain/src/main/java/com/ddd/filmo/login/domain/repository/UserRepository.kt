package com.ddd.filmo.login.domain.repository

import com.ddd.filmo.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun fetchUser(): User

    suspend fun isExitUser(userId: String): Flow<Boolean>

    fun saveUser(user: User): Flow<Boolean>

    fun deleteUser(): Boolean
}
