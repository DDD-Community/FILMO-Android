package com.ddd.filmo.login.data

import com.ddd.filmo.login.data.remote.UserRemoteDataSource
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override fun fetchUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun isExitUser(userId: String): Flow<Boolean> = flow {
        emit(userRemoteDataSource.isExitUser(userId))
    }.flowOn(Dispatchers.IO)

    override fun saveUser(user: User): Flow<Boolean> = flow {
        emit(userRemoteDataSource.saveUser(user))
    }.flowOn(Dispatchers.IO)

    override fun deleteUser(): Boolean {
        TODO("Not yet implemented")
    }
}
