package com.ddd.filmo.presentation.scene.ui.read

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene

@Composable
fun SceneReadScreen(scene: Scene, toEditScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var isDropDownMenuExpanded by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                IconButton(onClick = {

                }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        isDropDownMenuExpanded = true
                    },
                    modifier = Modifier.size(40.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_three_dots_horizontal),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                    )
                    DropdownMenu(
                        expanded = isDropDownMenuExpanded,
                        onDismissRequest = {
                            isDropDownMenuExpanded = false
                        },
                    ) {
                        // adding items
                        listOf("씬 수정하기", "씬 삭제하기").forEachIndexed { itemIndex, itemValue ->
                            DropdownMenuItem(
                                onClick = {
                                    isDropDownMenuExpanded = false
                                    toEditScreen()
                                },
                                text = { Text(text = itemValue) },
                            )
                        }
                    }
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
                for (i in 0 until scene.sceneRate!!) {
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
            AsyncImage(
                model = scene.sceneType,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(13.dp))
                    .aspectRatio(328f / 204f),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

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