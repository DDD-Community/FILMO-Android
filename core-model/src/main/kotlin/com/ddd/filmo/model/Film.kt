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
            caseColor = 0xFFCF68FF,
            name = "일이삼사오육칠팔구십일이삼사오육칠팔구십",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilm2 = Film(
            caseColor = 0xFFFCD4D2,
            name = "인생필름",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilm3 = Film(
            caseColor = 0xFFFF5C5D,
            name = "최애 로코영화",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilm4 = Film(
            caseColor = 0xFFBBEF4C,
            name = "Best1",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilm5 = Film(
            caseColor = 0xFF1FCF6A,
            name = "Best2",
            sceneCount = 10,
            isPrivate = false,
        )
        val fakeFilmList = listOf(fakeFilm0, fakeFilm1, fakeFilm2, fakeFilm3, fakeFilm4, fakeFilm5)
    }
}