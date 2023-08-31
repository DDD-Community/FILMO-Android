package com.ddd.filmo.login.data.model

import com.ddd.filmo.mapper.typemarker.DataModel

data class UserResponse(
    val email: String = "id",
    val name: String = "name",
    val nickName: String = "",
) : DataModel
