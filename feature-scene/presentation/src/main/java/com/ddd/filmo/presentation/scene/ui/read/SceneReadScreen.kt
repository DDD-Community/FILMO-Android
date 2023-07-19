package com.ddd.filmo.presentation.scene.ui.read

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.model.Scene

@Composable
internal fun SceneReadScreenRoute(
//    viewModel: MainViewModel = hiltViewModel(),
//    navigateToReadVote: (Int) -> Unit,
//    navigateToSearch: () -> Unit,
    scene: Scene,
) {
    SceneReadScreen(scene = scene)
}

@Composable
fun SceneReadScreen(scene: Scene) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = scene.movie?.posterImageUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(81.dp, 106.dp),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = scene.movie?.title ?: "", color = Color(0xff7A7289))
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = scene.movie?.releaseYear.toString(), color = Color(0xff7A7289))

        Row() {
            for (i in 0 until scene.sceneRate!!) {
                Text(text = "★", color = Color(0xffFFE144))
            }
        }

        Spacer(modifier = Modifier.size(30.dp))

        AsyncImage(
            model = scene.sceneImageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .aspectRatio(328f / 194f),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = scene.sceneText.toString(),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF332D41),
                letterSpacing = 0.14.sp,
            ),
            modifier = Modifier.align(Alignment.Start),
        )
    }
}
