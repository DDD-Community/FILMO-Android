package com.ddd.filmo.mapper

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.mapper.typemarker.DomainModel

interface DomainMapper<DATA : DataModel, DOMAIN : DomainModel> {
    fun toDomain(data: DATA): DOMAIN
}
