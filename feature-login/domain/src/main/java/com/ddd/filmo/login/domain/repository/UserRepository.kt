package com.ddd.filmo.login.domain.repository

interface UserRepository {

    fun fetchUser(): String

    fun isExitUser(): Boolean

    fun saveUser(user: String): Boolean

    fun deleteUser(): Boolean
}
