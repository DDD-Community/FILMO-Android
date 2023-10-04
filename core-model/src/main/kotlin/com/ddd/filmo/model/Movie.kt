package com.ddd.filmo.model

import com.ddd.filmo.mapper.typemarker.DomainModel

data class Movie(
    val title: String = "",
    val releaseYear: Int = 0,
    val posterImageUrl: String = "",
) : DomainModel {
    companion object {
        val mock = Movie(
            title = "노트북",
            releaseYear = 2004,
            posterImageUrl = "https://upload.wikimedia.org/wikipedia/en/8/86/Posternotebook.jpg",
        )

        val mock1 = Movie(
            title = "메트릭스1",
            releaseYear = 1999,
            posterImageUrl = "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg",
        )

        val mock2 = Movie(
            title = "메트릭스2",
            releaseYear = 2003,
            posterImageUrl = "https://upload.wikimedia.org/wikipedia/en/b/ba/Poster_-_The_Matrix_Reloaded.jpg",
        )

        val mock3 = Movie(
            title = "메트릭스3",
            releaseYear = 2003,
            posterImageUrl = "https://upload.wikimedia.org/wikipedia/en/3/34/Matrix_revolutions_ver7.jpg",
        )

        val mock4 = Movie(
            title = "메트릭스4",
            releaseYear = 2021,
            posterImageUrl = "https://upload.wikimedia.org/wikipedia/en/9/9f/The_Matrix_Resurrections.png",
        )

        val mockMovieList = listOf(mock, mock1, mock2, mock3, mock4)
    }
}
