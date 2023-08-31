package com.ddd.filmo.login.data.model

import com.ddd.filmo.mapper.typemarker.DataModel

data class UserResponse(
    val userId: String? = null,
    val email: String = "id",
    val name: String = "name",
    val nickName: String = "",
) : DataModel
