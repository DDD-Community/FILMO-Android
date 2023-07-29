package com.ddd.filmo.model

import java.text.SimpleDateFormat
import java.util.Date

data class Scene(
    val sceneId: String?,
    val sceneText: String?,
    val sceneImageUrl: String?,
    val sceneRate: Int?,
    val movie: Movie?,
    val createdAt: Date,
) {
    val createdAtString = sceneSdf.format(createdAt)
    companion object {
        val sceneSdf = SimpleDateFormat("yyyy.MM.dd")

        val mock = Scene(
            sceneId = "1",
            sceneText = "A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom. However, social differences soon get in the way.",
            sceneImageUrl = "https://hips.hearstapps.com/hmg-prod/images/notebook-1551291989.jpg?crop=0.666xw:1.00xh;0.173xw,0&resize=1200:*",
            sceneRate = 5,
            movie = Movie.mock,
            createdAt = Date(),
        )

        val mock1 = Scene(
            sceneId = "2",
            sceneText = "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            sceneImageUrl = "https://image.cine21.com/resize/cine21/still/2017/0304/18_07_58__58ba83ee3e0d3[W680-].jpg",
            sceneRate = 5,
            movie = Movie.mock1,
            createdAt = Date(),
        )
    }
}
