package com.ddd.filmo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ddd.filmo.core.ui.R
import com.ddd.filmo.model.Movie
import com.ddd.filmo.model.Scene
import com.ddd.filmo.model.SceneType
import java.util.Date

@Composable
fun SceneImage(scene: Scene, navigateToSceneDetail: () -> Unit) {
    Box(
        Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navigateToSceneDetail()
            },
    ) {
        when (scene.sceneType) {
            is SceneType.ImageDrawable -> {
                Image(
                    painter = painterResource(id = (scene.sceneType as SceneType.ImageDrawable).imageDrawable),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(218 / 133f)
                        .background(color = Color(0xff625B71)),
                    contentScale = ContentScale.Crop,
                )
            }

            is SceneType.ImageUrl -> AsyncImage(
                model = (scene.sceneType as SceneType.ImageUrl).imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(218 / 133f)
                    .background(color = Color(0xff625B71)),
                contentScale = ContentScale.Crop,
            )

            is SceneType.Color -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color((scene.sceneType as SceneType.Color).color)),
                )
            }

            null -> {
            }
        }

        Text(
            text = "${scene.sceneText}",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(14.dp),
            color = Color.White,
            fontWeight = FontWeight.W500,
            maxLines = 2,
        )
        Text(
            text = "${scene.movie?.title}\n${Scene.mock.movie?.releaseYear}",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(14.dp)
                .align(Alignment.BottomStart),
            color = Color.White,
            fontWeight = FontWeight.W500,
        )
    }
}

object SceneImageTest {
    val FirstSceneType = listOf<Scene>(
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_16),
            movie = Movie(
                title = "About Time",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_17),
            movie = Movie(
                title = "어벤져스: 엔드게임",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
    )
}

@Preview
@Composable
fun SceneImagePreview() {
    SceneImage(scene = SceneImageTest.FirstSceneType.first(), {})
}
