package com.ddd.filmo.login.data.remote

import com.ddd.filmo.login.data.model.UserResponse
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

interface UserRemoteDataSource {
    fun getUser(): UserResponse
    fun isExitUser(): Boolean
    fun saveUser(user: UserResponse): Boolean
    fun deleteUser(): Boolean
}

class UserRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : UserRemoteDataSource {
    override fun getUser(): UserResponse {
        TODO("Not yet implemented")
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
