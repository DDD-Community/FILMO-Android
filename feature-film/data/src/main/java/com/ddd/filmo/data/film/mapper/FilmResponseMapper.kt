package com.ddd.filmo.data.film.mapper

import com.ddd.filmo.data.film.model.FilmResponse
import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.Film

object FilmResponseMapper : DomainMapper<FilmResponse, Film> {
    override fun toDomain(data: FilmResponse): Film {
        return Film(
            documentId = data.documentId,
            caseColor = data.caseColor,
            name = data.name,
            sceneCount = data.sceneCount,
            isPrivate = data.isPrivate,
            createdAt = data.createdAt,
            scenes = data.scenes
        )
    }
}
