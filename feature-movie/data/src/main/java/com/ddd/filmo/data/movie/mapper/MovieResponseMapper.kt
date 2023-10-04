package com.ddd.filmo.data.movie.mapper

import com.ddd.filmo.mapper.DomainMapper
import com.ddd.filmo.model.Movie
import com.ddd.filmo.network.model.MovieResponse

object MovieResponseMapper : DomainMapper<MovieResponse, Movie> {
    override fun toDomain(data: MovieResponse): Movie {
        return with(data) {
            Movie(
                title = title,
                releaseYear = data.repRlsDate.toInt(),
                posterImageUrl = data.posterUrl,
            )
        }
    }
}
