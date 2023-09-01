package com.ddd.filmo.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film
import com.ddd.filmo.presentation.scene.ui.detail.AddFilmDialog
import com.ddd.filmo.ui.FilmCase
import com.ddd.filmo.ui.FilmCaseAdd

@Composable
fun MainScreen(
    navigateToFilmDetail: () -> Unit = {},
    navigateToMyPage: () -> Unit = {},
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val userInfo = viewModel.user.collectAsState()

    val filmList = listOf(
        Film(0xFF9868FF, "Basic", 2000, true),
        Film(0xFFCF68FF, "Disney", 1000, true),
        Film(0xFFFF97CA, "하하하하하하하하하하하하하하하하하하하하", 17, false),
        Film(0xFF1FCF6A, "My Best", 999, false),
        Film(0xFFF5DF1A, "나의 인생 영화 목록", 999, true),
    )
    val gradient = Brush.verticalGradient(
        listOf(Color(0x007918F2), Color(0x203401FF), Color(0x207918F2)),
    )

    var filmAddDialogState by remember { mutableStateOf(false) }

    if (filmAddDialogState) {
        AddFilmDialog(onDismissRequest = { filmAddDialogState = false })
    }

    Box(
        modifier = Modifier
            .background(Color(0xff202020))
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .height(500.dp),
        )
        FilmoAppBar(
            actions = {
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = navigateToMyPage,
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = FilmoIcon.MyPage),
                        contentDescription = "MyPage",
                    )
                }
            },
            navigationIcon = {
            },

        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = "${userInfo.value?.name}, ${userInfo.value?.userId}",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 30.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFDDDDDD),
                    textAlign = TextAlign.Center,
                ),
            )
            Text(
                text = "기록하고 싶은 장면을 \n나만의 씬으로 만들어 보세요",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 30.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFDDDDDD),
                    textAlign = TextAlign.Center,
                ),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff553EFF),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
                modifier = Modifier.size(160.dp, 40.dp),
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "씬 만들기",
                    fontFamily = FilmoFamily,
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.size(40.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(
                    top = 32.dp,
                    bottom = 32.dp,
                    start = 17.dp,
                    end = 17.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color(0xff2A2A2A)),
            ) {
                item {
                    FilmCaseAdd(filmList.size, onClickFilm = {
                        filmAddDialogState = !filmAddDialogState
                    })
                }
                items(filmList) { film ->
                    FilmCase(film = film, onClickFilm = navigateToFilmDetail)
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
    ) {
    }
}
