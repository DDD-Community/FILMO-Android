package com.ddd.filmo.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.icon.FilmoIcon

@Composable
fun MainScreen(
    navigateToFilmDetail: () -> Unit = {},
) {
    val filmList = listOf(
        Film(Color(0xFF9868FF), "Basic", 2000, true),
        Film(Color(0xFFCF68FF), "Disney", 1000, true),
        Film(Color(0xFFFF97CA), "하하하하하하하하하하하하하하하하하하하하", 17, false),
        Film(Color(0xFF1FCF6A), "My Best", 999, false),
        Film(Color(0xFFF5DF1A), "나의 인생 영화 목록", 999, true),
    )
    val gradient = Brush.verticalGradient(
        listOf(Color(0x007918F2), Color(0x203401FF), Color(0x207918F2))
    )
    Box(
        modifier = Modifier
            .background(Color(0xff202020))
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .height(500.dp)
        )
        TopBar()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(60.dp))
            Text(
                text = "기록하고 싶은 장면을 \n나만의 씬으로 만들어 보세요",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFDDDDDD),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "씬 만들기", color = Color.White)
            }
            Spacer(modifier = Modifier.size(40.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(
                    top = 32.dp,
                    bottom = 32.dp,
                    start = 17.dp,
                    end = 17.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color(0xff2A2A2A)),
            ) {
                item {
                    FilmCaseAdd(filmList.size)
                }
                items(filmList) { film ->
                    FilmCase(film, navigateToFilmDetail)
                }
            }
        }
    }
}

data class Film(
    val caseColor: Color = Color(0xFF9868FF),
    val name: String = "",
    val sceneCount: Int = 0,
    val isPrivate: Boolean = false,
)

@Composable
fun FilmCase(
    film: Film = Film(),
    navigateToFilmDetail: () -> Unit = {},
) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(85.dp)
                .background(
                    color = Color(0xFF5D658B),
                    shape = RoundedCornerShape(size = 15.dp)
                )
        )
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    navigateToFilmDetail()
                }
                .height(210.dp)
                .fillMaxWidth()
                .background(film.caseColor)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                if (film.isPrivate) {
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .background(color = Color(0x1A000000), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = FilmoIcon.Lock),
                            tint = Color(0xDDFFFFFF),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .background(color = Color(0x1A000000), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    val sceneCountString = if (film.sceneCount >= 1000) {
                        (film.sceneCount / 1000).toString() + "k"
                    } else {
                        film.sceneCount.toString()
                    }
                    Text(
                        text = sceneCountString,
                        color = Color.White,
                        fontSize = 13.sp,
                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
                text = film.name,
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 33.6.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3C3B3E),
                    letterSpacing = 0.24.sp,
                ),
                maxLines = 4,
            )
        }
    }
}

@Composable
fun FilmCaseAdd(filmCount: Int) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(85.dp)
                .background(
                    color = Color(0xFF5D658B),
                    shape = RoundedCornerShape(size = 15.dp)
                )
        )
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(210.dp)
                .fillMaxWidth()
                .background(Color(0xFF303030)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = FilmoIcon.Add),
                    tint = Color(0xff7D7A7A),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "필름 추가하기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.16.sp,
                    )
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "(${filmCount}/24)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF7D7A7A),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.12.sp,
                    )
                )
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {

    }
}