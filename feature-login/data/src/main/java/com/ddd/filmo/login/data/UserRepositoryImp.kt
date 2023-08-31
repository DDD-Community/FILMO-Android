package com.ddd.filmo.login.data

import com.ddd.filmo.login.data.model.UserResponse
import com.ddd.filmo.login.domain.repository.UserRepository

class UserRepositoryImp : UserRepository {

    override fun fetchUser(): UserResponse {

    }

    override fun isExitUser(): Boolean {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: UserResponse): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteUser(): Boolean {
        TODO("Not yet implemented")
    }
}
