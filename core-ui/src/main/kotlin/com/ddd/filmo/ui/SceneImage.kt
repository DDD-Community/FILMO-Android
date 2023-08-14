package com.ddd.filmo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
fun SceneImage(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    scene: Scene,
    navigateToSceneDetail: () -> Unit,
) {
    Box(
        modifier
            .padding(paddingValues)
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
                        .aspectRatio(218 / 133f)
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
    val firstSceneType = listOf<Scene>(
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
            sceneText = "“시작은 막차였다”",
            sceneType = SceneType.fromColor(0xFFFCD4D2),
            movie = Movie(
                title = "꽃다발 같은 사랑을 했다",
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
        Scene(
            sceneId = "1",
            sceneText = "힐링이 필요할 때,\n" +
                "지루한 듯 하지만 지루하지 않은 영화",
            sceneType = SceneType.fromColor(0xFFFCD4D2),
            movie = Movie(
                title = "리틀 포레스트: 여름과 가을",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_18),
            movie = Movie(
                title = "빅 피쉬",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "멀지 않은 미래, 새로운 형태의 사랑",
            sceneType = SceneType.fromColor(0xFFFF97CA),
            movie = Movie(
                title = "Her",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
    )
    val secondSceneType = listOf<Scene>(
        Scene(
            sceneId = "1",
            sceneText = "겨울마다 보는 내 인생 뮤지컬 영화",
            sceneType = SceneType.fromColor(0xFFFFCE4F),
            movie = Movie(
                title = "라라랜드",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_19),
            movie = Movie(
                title = "엘리멘탈",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "“ Please, let me keep this memory,\n" +
                "just this moment.”",
            sceneType = SceneType.fromColor(0xFFCF68FF),
            movie = Movie(
                title = "이터널 선샤인",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_21),
            movie = Movie(
                title = "클래식",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "꽉 닫힌 해피엔딩\n" +
                "장면과 너무나도 잘 어울리는 음악\n" +
                "언제 봐도 마음이 편안해진다.",
            sceneType = SceneType.fromColor(0xFF9CCEFF),
            movie = Movie(
                title = "노팅 힐",
                releaseYear = 2013,
                posterImageUrl = "",
            ),
            sceneRate = 5,
            createdAt = Date(),
        ),
        Scene(
            sceneId = "1",
            sceneText = "",
            sceneType = SceneType.fromDrawable(R.drawable.image_23),
            movie = Movie(
                title = "화양연화",
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
    SceneImage(scene = SceneImageTest.firstSceneType.first(), navigateToSceneDetail = {})
}

@Preview
@Composable
fun SceneImageColorPreview() {
    SceneImage(scene = SceneImageTest.firstSceneType[2], navigateToSceneDetail = {})
}
