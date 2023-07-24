package com.ddd.filmo.presentation.film.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.model.Scene
import java.util.Date

@Composable
fun FilmDetailScreen(
    toAddScene: (Scene?) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val sdf = java.text.SimpleDateFormat("yy.MM.dd")
    val date = sdf.format(Date())

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff202020))
    ) {
        Row(Modifier.padding(10.dp)) {
            IconButton(onClick = {
                onBackClick()
            }, modifier = Modifier.size(40.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}, modifier = Modifier.size(40.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_three_dots_horizontal),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Box() {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(
                        color = Color(0xCCCF68FF),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
                content = {
                    item {
                        Spacer(modifier = Modifier.size(104.dp))
                    }
                    for (i in 0..4) {
                        item {
                            Box(
                                Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        toAddScene(Scene.mock)
                                    },
                            ) {
                                AsyncImage(
                                    model = Scene.mock.sceneImageUrl,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(218 / 133f)
                                        .background(color = Color(0xff625B71)),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(
                                    text = "${Scene.mock.sceneText}",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(14.dp),
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    maxLines = 2,
                                )
                                Text(
                                    text = "${Scene.mock.movie?.title}\n${Scene.mock.movie?.releaseYear}",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier
                                        .padding(14.dp)
                                        .align(BottomStart),
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                )
                            }
                        }
                        item {
                            Box(
                                Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        toAddScene(Scene.mock1)
                                    },
                            ) {
                                AsyncImage(
                                    model = Scene.mock1.sceneImageUrl,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(218 / 133f)
                                        .background(color = Color(0xff625B71)),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(
                                    text = "${Scene.mock1.sceneText}",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier.padding(14.dp),
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    maxLines = 2,
                                )
                                Text(
                                    text = "${Scene.mock1.movie?.title}\n${Scene.mock1.movie?.releaseYear}",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier
                                        .padding(14.dp)
                                        .align(BottomStart),
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                )
                            }
                        }
                    }
                },
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp, end = 8.dp)
                    .width(46.dp)
                    .height(48.dp)
                    .background(color = Color(0xFF5D658B), shape = RoundedCornerShape(size = 15.dp))
                    .align(TopEnd)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 16.dp)
                    .background(color = Color(0xFFCF68FF), shape = RoundedCornerShape(size = 20.dp)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = "Disney",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 25.2.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF282629),
                        letterSpacing = 0.18.sp,
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "9999",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 25.2.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF505050),
                        letterSpacing = 0.18.sp,
                    )
                )
                Spacer(modifier = Modifier.size(24.dp))
            }

            ExtendedFloatingActionButton(
                onClick = {
                    toAddScene(null)
                },
                modifier = Modifier
                    .align(BottomEnd)
                    .padding(end = 22.dp, bottom = 24.dp),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                Spacer(modifier = Modifier.width(width = 12.dp))
                Text(text = "씬 가져오기")
            }
        }
    }
}
