package com.ddd.filmo.model

data class Film(
    val caseColor: Long = 0xFF9868FF,
    val name: String = "",
    val sceneCount: Int = 0,
    val isPrivate: Boolean = false,
) {
    companion object {
        val fakeFilm0 = Film(
            0xFF9868FF,
            "Basic",
            2000,
            true
        )
        val fakeFilm1 = Film(
            caseColor = 0xFFFF0000,
            name = "아무 필름",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilm2 = Film(
            caseColor = 0xFF00FF00,
            name = "아무무 필름",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilmList = listOf(fakeFilm0, fakeFilm1, fakeFilm2)
    }
}