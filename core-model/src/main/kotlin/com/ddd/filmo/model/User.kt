package com.ddd.filmo.model

import com.ddd.filmo.mapper.typemarker.DomainModel

data class User(
    val email: String = "id",
    val name: String = "name",
    val nickName: String = "",
) : DomainModel
