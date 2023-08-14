package com.ddd.filmo.presentation.scene.ui.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.model.Film


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneEditScreen(scene: Scene) {
    val selectedFilm = remember { mutableStateOf(Film.fakeFilm0) }
    val selectedUri = remember { mutableStateOf<Uri?>(null) }
    val sceneText = remember { mutableStateOf("A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom. However, social differences soon get in the way. \n\n A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom. However, social differences soon get in the way.")}
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedUri.value = uri }
    )

    Column(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color(0xFF393939), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 16.dp, end = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "스파이더맨: 어크로스 더 유니버스",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFF4F4F4),
                    )
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = FilmoIcon.X),
                        contentDescription = "X",
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "별점을 클릭해 주세요.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Spacer(modifier = Modifier.width(8.dp))
                    for (i in 0..4) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = FilmoIcon.Star),
                            contentDescription = "Star",
                            tint = FilmoColor.PrimaryVariant,
                            modifier = Modifier.size(37.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(size = 8.dp))
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                    .dashedBorder(
                        strokeWidth = 1.dp,
                        color = FilmoColor.ic_02,
                        cornerRadiusDp = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (selectedUri.value == null) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Icon(
                        painter = painterResource(id = FilmoIcon.PlusWithCircle),
                        contentDescription = "Add",
                        tint = FilmoColor.PrimaryVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "이미지를 등록해 주세요. (선택)",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFF4F4F4),
                        )
                    )
                    Text(
                        text = "10mb 이하",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.8.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFB6B6B6),
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                } else {
                    AsyncImage(
                        model = selectedUri.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(13.dp))
                            .aspectRatio(328f / 204f),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = "${sceneText.value}",
                onValueChange = { sceneText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 294.dp)
                    .background(color = Color(0xFF393939), shape = RoundedCornerShape(size = 8.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 8.dp))
                    .clickable { selectedFilm.value = Film.fakeFilm1 }
                    .border(
                        width = 1.dp,
                        color = Color(0xFF7D7A7A),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .fillMaxWidth()
                    .height(58.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = FilmoIcon.Folder),
                    contentDescription = "image description",
                    tint = Color(selectedFilm.value.caseColor),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "위치: ${selectedFilm.value.name}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFF4F4F4),
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }

        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FilmoColor.PrimaryVariant,
                    contentColor = Color(0xFFF4F4F4),
                ),
                shape = RoundedCornerShape(size = 8.dp),
            ) {
                Text(
                    text = "씬 만들기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFF4F4F4),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.16.sp,
                    )
                )
            }
        }

    }

}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)
