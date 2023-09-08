package com.ddd.filmo.presentation.scene.ui.read

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.designsystem.component.bottom.FilmoChoiceBottomSheetDialog
import com.ddd.filmo.designsystem.component.dialog.FilmoDialog
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene
import com.ddd.filmo.model.SceneType
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneReadScreen(
    scene: Scene,
    toEditScreen: () -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
) {
    var isSceneDialogState by remember { mutableStateOf(false) }
    var isSceneDeleteDialogState by remember { mutableStateOf(false) }

    if (isSceneDialogState) {
        FilmoChoiceBottomSheetDialog(
            choiceList = listOf(
                "씬 수정하기",
                "씬 삭제하기",
                "취소하기",
            ),
            onItemClicked = {
                when (it) {
                    0 -> toEditScreen()
                    1 -> isSceneDeleteDialogState = true
                    2 -> isSceneDialogState = false
                }
                isSceneDialogState = false
            },
            onDismissRequest = {
                isSceneDialogState = false
            },
        )
    }

    if (isSceneDeleteDialogState) {
        SceneDeleteDialog(
            onAcceptClicked = {},
            onCancelClicked = {
                isSceneDeleteDialogState = false
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                IconButton(onClick = onBackButtonClicked, modifier = Modifier.size(40.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        isSceneDialogState = true
                    },
                    modifier = Modifier.size(40.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_three_dots_horizontal),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2A2A2A))
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = scene.movie?.title ?: "",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF4F4F4),
                    letterSpacing = 0.14.sp,
                ),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row() {
                for (i in 0 until scene.sceneRate!!.roundToInt()) {
                    Text(text = "★", color = FilmoColor.PrimaryVariant)
                }
            }
        }

        /*Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2A2A2A))
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = scene.movie?.posterImageUrl,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(48.dp, 69.dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = scene.movie?.title ?: "",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFF4F4F4),
                        letterSpacing = 0.14.sp,
                    ),
                )
                Text(
                    text = scene.movie?.releaseYear.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 22.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFB6B6B6),
                        letterSpacing = 0.12.sp,
                    ),
                )
                Spacer(modifier = Modifier.size(9.dp))
                Row() {
                    for (i in 0 until scene.sceneRate!!) {
                        Text(text = "★", color = FilmoColor.PrimaryVariant)
                    }
                }
            }
        }*/

        Spacer(modifier = Modifier.size(32.dp))

        Column(Modifier.padding(horizontal = 16.dp)) {
            if (scene.sceneType is SceneType.ImageUrl) {
                AsyncImage(
                    model = (scene.sceneType as SceneType.ImageUrl).imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(13.dp))
                        .aspectRatio(328f / 204f),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = scene.sceneText.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4F4),
                ),
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = scene.createdAtString,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFB6B6B6),
                    letterSpacing = 0.14.sp,
                ),
            )
        }
    }
}

@Composable
internal fun SceneDeleteDialog(onAcceptClicked: () -> Unit = {}, onCancelClicked: () -> Unit = {}) {
    FilmoDialog(
        onAcceptClicked = onAcceptClicked,
        onCancelClicked = onCancelClicked,
        cancelText = "취소하기",
        acceptText = "삭제하기",
        cancelColors = ButtonDefaults.buttonColors(
            containerColor = FilmoColor.txt_03,
        ),
        acceptColors = ButtonDefaults.buttonColors(
            containerColor = FilmoColor.film_color_05,
        ),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "씬 삭제하기",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(700),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp,
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "씬 삭제 시 기록을 복구 할 수 없습니다.\n" +
                    "정말 삭제하시겠어요?",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.16.sp,
                ),
            )
        }
    }
}

@Preview
@Composable
fun SceneReadScreenPreview() {
    SceneReadScreen(scene = Scene.mock1) {
    }
}

@Preview
@Composable
fun SceneDeleteDialogPreview() {
    SceneDeleteDialog(
        onAcceptClicked = {},
        onCancelClicked = {},
    )
}
