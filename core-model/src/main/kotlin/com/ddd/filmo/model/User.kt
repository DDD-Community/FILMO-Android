package com.ddd.filmo.model

import com.ddd.filmo.mapper.typemarker.DomainModel

data class User(
    val userId: String = "",
    val email: String = "id",
    val name: String = "name",
    val nickName: String = "",
    val loginType: LoginType = LoginType.GOOGLE,
) : DomainModel

enum class LoginType {
    GOOGLE,
    KAKAO,
}

object GoogleUser {
    var user = User(
        userId = "",
        email = "",
        name = "",
        nickName = "",
    )
}
