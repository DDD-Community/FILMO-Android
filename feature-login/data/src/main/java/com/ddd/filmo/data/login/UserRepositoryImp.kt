package com.ddd.filmo.data.login

import com.ddd.filmo.core.util.IoDispatcher
import com.ddd.filmo.core.util.MainDispatcher
import com.ddd.filmo.data.login.remote.UserRemoteDataSource
import com.ddd.filmo.login.domain.repository.UserRepository
import com.ddd.filmo.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {
    val _currentUser: MutableStateFlow<User?> = MutableStateFlow(null)
    override val currentUser: StateFlow<User?> = _currentUser

    init {
        CoroutineScope(coroutineDispatcher).launch {
            fetchUser()
        }
    }

    override suspend fun fetchUser() {
        val userResponse = userRemoteDataSource.getUser()
        _currentUser.value = User(
            userId = userResponse.userId.toString(),
            email = userResponse.email,
            name = userResponse.name,
            nickName = userResponse.nickName,
        )
    }

    override suspend fun isExitUser(userId: String): Flow<Boolean> = flow {
        emit(userRemoteDataSource.isExitUser(userId))
    }.flowOn(coroutineDispatcher)

    override fun saveUser(user: User): Flow<Boolean> = flow {
        emit(userRemoteDataSource.saveUser(user))
    }.flowOn(coroutineDispatcher)

    override fun deleteUser(): Boolean {
        TODO("Not yet implemented")
    }
}
