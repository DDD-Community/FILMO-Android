package com.ddd.filmo.film.data.mapper

import com.ddd.filmo.film.data.model.FilmResponse
import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.Film

object FilmResponseMapper : DomainMapper<FilmResponse, Film> {
    override fun toDomain(data: FilmResponse): Film {
        return Film(
            caseColor = data.caseColor,
            name = data.name,
            sceneCount = data.sceneCount,
            isPrivate = data.isPrivate,
            createdAt = data.createdAt,
        )
    }
}
