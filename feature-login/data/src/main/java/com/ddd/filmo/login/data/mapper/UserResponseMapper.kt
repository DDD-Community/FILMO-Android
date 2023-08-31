package com.ddd.filmo.login.data.mapper

import com.ddd.filmo.login.data.model.UserResponse
import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.User

object UserResponseMapper : DomainMapper<UserResponse, User> {
    override fun toDomain(data: UserResponse): User {
        return User(
            email = data.email,
            name = data.name,
            nickName = data.nickName,
        )
    }
}
