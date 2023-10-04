package com.ddd.filmo.presentation.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.textfield.FilmoOutlinedTextField
import com.ddd.filmo.designsystem.component.textfield.FilmoTextFieldLeadingType
import com.ddd.filmo.designsystem.component.textfield.FilmoTextFieldTrailingType
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Movie

@Composable
fun SearchMovieScreen(movieList: List<Movie>? = null) {
    Column {
        FilmoAppBar(title = "영화 검색", actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = FilmoIcon.X),
                    contentDescription = "X",
                    modifier = Modifier.size(12.dp),
                )
            }
        })

        FilmoOutlinedTextField(
            value = "",
            onValueChanged = {},
            placeholderText = "영화를 선택해 주세요.",
            trailingType = FilmoTextFieldTrailingType.CLEAR,
            leadingType = FilmoTextFieldLeadingType.SEARCH,
        )
        LazyColumn {
            if (movieList.isNullOrEmpty()) {
                item {
                    Text(text = "영화 제목으로 검색해 보세요.")
                }
            } else {
                items(movieList) {
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row {
        AsyncImage(
            model = movie.posterImageUrl,
            contentDescription = "영화 포스터",
            modifier = Modifier.size(100.dp),
        )
        Column {
            Text(
                text = movie.title,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = FilmoColor.txt_01,
                    letterSpacing = 0.14.sp,
                )
            )
            Text(
                text = movie.releaseYear.toString(),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_02,
                    letterSpacing = 0.12.sp,
                )
            )
        }
    }
}

@Preview
@Composable
fun SearchMovieScreenPreview() {
    SearchMovieScreen()
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(movie = Movie(title = "영화 제목", releaseYear = 2023))
}
