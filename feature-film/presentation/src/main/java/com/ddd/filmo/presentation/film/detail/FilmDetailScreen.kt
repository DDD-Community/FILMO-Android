package com.ddd.filmo.presentation.film.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.model.Scene
import com.ddd.filmo.ui.SceneImage
import java.util.Date

@Composable
fun FilmDetailScreen(
    toAddScene: (Scene?) -> Unit = {},
    onBackClick: () -> Unit = {},
    navigateToSceneDetail: () -> Unit = {},
    viewModel: FilmDetailViewModel = hiltViewModel()
) {
    val selectedFilm = viewModel.selectedFilm.collectAsStateWithLifecycle().value
    val sdf = java.text.SimpleDateFormat("yy.MM.dd")
    val date = sdf.format(Date())

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff202020)),
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
            val caseColor = Color(selectedFilm.caseColor)
            val modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .alpha(0.8f)
                    .background(
                        color = caseColor,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    )
            )

            LazyColumn(
                modifier = modifier,
                content = {
                    item {
                        Spacer(modifier = Modifier.size(104.dp))
                    }
                    for (i in 0..4) {
                        item {
                            SceneImage(
                                scene = Scene.mock,
                                navigateToSceneDetail = navigateToSceneDetail
                            )
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
                    .align(TopEnd),
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 16.dp)
                    .background(
                        color = caseColor,
                        shape = RoundedCornerShape(size = 20.dp),
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = selectedFilm.name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 25.2.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF282629),
                        letterSpacing = 0.18.sp,
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = selectedFilm.sceneCount.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 25.2.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF505050),
                        letterSpacing = 0.18.sp,
                    ),
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

@Preview
@Composable
fun FilmDetailScreenPreview() {
    FilmDetailScreen()
}
