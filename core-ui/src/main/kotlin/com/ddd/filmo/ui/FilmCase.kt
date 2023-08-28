package com.ddd.filmo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film

/**
 * 메인에 사용하는 필름통
 *
 * @param modifier
 * @param film
 * @param filmSize
 * @param onClickFilm
 */
@Composable
fun FilmCase(
    modifier: Modifier = Modifier.fillMaxWidth(),
    film: Film = Film(),
    filmSize: FilmSize = FilmSize.Large,
    onClickFilm: () -> Unit = {},
) {
    FilmBody(
        modifier = modifier.clickable {
            onClickFilm()
        },
        color = Color(film.caseColor),
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

@Composable
fun FilmCaseAdd(filmCount: Int, onClickFilm: () -> Unit = {}) {
    FilmBody(modifier = Modifier.clickable { onClickFilm() }, color = Color(0xFF303030)) {
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
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

@Composable
fun FilmBody(
    modifier: Modifier = Modifier,
    color: Color,
    filmSize: FilmSize = FilmSize.Large,
    isClicked: Boolean = false,
    content: @Composable BoxScope.() -> Unit,

) {
    BoxWithConstraints(
        modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        val width = this.maxWidth
        val capSize = width / 5
        val capHeight = capSize

        Box(
            modifier = Modifier
                .width(width / 2)
                .height(capHeight)
                .background(
                    color = Color(0xFF5D658B),
                    shape = RoundedCornerShape(size = capSize),
                ),
        )
        Box(
            modifier = Modifier
                .padding(top = capHeight / 2)
                .clip(RoundedCornerShape(filmSize.roundSize))
                .fillMaxWidth()
                .aspectRatio(1 / 1.3f)
                .background(color)
                .then(
                    if (isClicked) {
                        Modifier.border(
                            1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(filmSize.roundSize),
                        )
                    } else {
                        Modifier
                    },
                ),
            content = content,
        )
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

enum class FilmSize(val width: Dp, val height: Dp, val capSize: Dp, val roundSize: Dp) {
    Small(width = 48.dp, capSize = 24.dp, height = 68.dp, roundSize = 10.dp),
    Large(width = 156.dp, capSize = 80.dp, height = 226.dp, roundSize = 20.dp),
}
