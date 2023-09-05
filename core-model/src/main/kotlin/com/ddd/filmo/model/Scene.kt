package com.ddd.filmo.model

import com.ddd.filmo.mapper.typemarker.DomainModel
import java.text.SimpleDateFormat
import java.util.Date

data class Scene(
    val documentId: String? = "",
    val sceneText: String? = "",
    val sceneType: SceneType? = SceneType.fromColor(0xff000000),
    val sceneRate: Float? = null,
    val movie: Movie? = null,
    val createdAt: Date = Date(),
):DomainModel {
    val createdAtString = sceneSdf.format(createdAt)

    companion object {
        val sceneSdf = SimpleDateFormat("yyyy.MM.dd")

        val mock = Scene(
            documentId = "1",
            sceneText = "다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 ",
            sceneType = SceneType.fromUrl("https://hips.hearstapps.com/hmg-prod/images/notebook-1551291989.jpg?crop=0.666xw:1.00xh;0.173xw,0&resize=1200:*"),
            sceneRate = 5f,
            movie = Movie.mock,
            createdAt = Date(),
        )

        val mock1 = Scene(
            documentId = "2",
            sceneText = "다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 다람쥐 헌 쳇바퀴에 타고파 ",
            sceneType = SceneType.fromUrl("https://image.cine21.com/resize/cine21/still/2017/0304/18_07_58__58ba83ee3e0d3[W680-].jpg"),
            sceneRate = 5f,
            movie = Movie.mock1,
            createdAt = Date(),
        )

        val mock2 = Scene(
            documentId = "2",
            sceneText = "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            sceneType = SceneType.fromUrl("https://image.cine21.com/resize/cine21/still/2017/0304/18_07_58__58ba83ee3e0d3[W680-].jpg"),
            sceneRate = 5f,
            movie = Movie.mock1,
            createdAt = Date(),
        )
    }
}

sealed interface SceneType {
    @JvmInline
    value class ImageUrl(val imageUrl: String) : SceneType

    @JvmInline
    value class ImageDrawable(val imageDrawable: Int) : SceneType

    @JvmInline
    value class Color(val color: Long) : SceneType

    companion object {
        fun fromUrl(imageUrl: String): SceneType = ImageUrl(imageUrl)
        fun fromDrawable(imageDrawable: Int): SceneType = ImageDrawable(imageDrawable)
        fun fromColor(imageColor: Long): SceneType = Color(imageColor)
    }
}
