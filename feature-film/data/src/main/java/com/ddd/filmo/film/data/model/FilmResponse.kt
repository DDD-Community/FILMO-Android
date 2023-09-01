package com.ddd.filmo.film.data.model

import com.ddd.filmo.mapper.typemarker.DataModel
import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.SceneType
import java.util.Date

data class FilmResponse(
    val caseColor: Long = 0xFF9868FF,
    val name: String = "",
    val sceneCount: Int = 0,
    val isPrivate: Boolean = false,
) : DataModel
