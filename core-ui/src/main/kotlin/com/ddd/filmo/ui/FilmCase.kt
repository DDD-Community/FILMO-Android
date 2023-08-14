package com.ddd.filmo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film

@Composable
fun FilmCase(
    modifier: Modifier = Modifier.fillMaxWidth(),
    film: Film = Film(),
    navigateToFilmDetail: () -> Unit = {},
) {
    Box(
        modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(85.dp)
                .background(
                    color = Color(0xFF5D658B),
                    shape = RoundedCornerShape(size = 15.dp),
                ),
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
                .background(Color(film.caseColor)),
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                if (film.isPrivate) {
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp)
                            .background(color = Color(0x1A000000), shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = FilmoIcon.Lock),
                            tint = Color(0xDDFFFFFF),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .background(color = Color(0x1A000000), shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    val sceneCountString = if (film.sceneCount >= 1000) {
                        (film.sceneCount / 1000).toString() + "k"
                    } else {
                        film.sceneCount.toString()
                    }
                    Text(
                        text = sceneCountString,
                        fontFamily = FilmoFamily,
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
                    fontFamily = FilmoFamily,
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
                    shape = RoundedCornerShape(size = 15.dp),
                ),
        )
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(210.dp)
                .fillMaxWidth()
                .background(Color(0xFF303030)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = FilmoIcon.Add),
                    tint = Color(0xff7D7A7A),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "필름 추가하기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.16.sp,
                    ),
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "($filmCount/24)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF7D7A7A),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.12.sp,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
fun FilmCasePreview() {
    FilmCase()
}

@Preview
@Composable
fun FilmCaseAddPreview() {
    FilmCaseAdd(4)
}
