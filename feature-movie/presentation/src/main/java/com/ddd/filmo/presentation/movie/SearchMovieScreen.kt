package com.ddd.filmo.presentation.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
fun SearchMovieScreenRoute(
    viewModel: SearchMovieViewModel = hiltViewModel(),
    navigateToBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchMovieScreen(uiState)
}

@Composable
internal fun SearchMovieScreen(searchMovieUiState: SearchUiState, onBackClicked: () -> Unit = {}) {
    var searchQueryState by remember { mutableStateOf("") }
    Column {
        FilmoAppBar(title = "영화 검색", actions = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = FilmoIcon.X),
                    contentDescription = "X",
                    modifier = Modifier.size(12.dp)
                )
            }
        })

        FilmoOutlinedTextField(
            value = searchQueryState,
            onValueChanged = { searchQueryState = it },
            placeholderText = "영화를 선택해 주세요.",
            trailingType = FilmoTextFieldTrailingType.CLEAR,
            leadingType = FilmoTextFieldLeadingType.SEARCH,
            leadingButtonClicked = {},
            trailingButtonClicked = {}
        )
        when (searchMovieUiState) {
            SearchUiState.Empty ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "검색 결과가 없어요.",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = FilmoColor.txt_02,
                            textAlign = TextAlign.Center
                        )
                    )
                }

            is SearchUiState.Error -> TODO()
            SearchUiState.Loading -> {}
            SearchUiState.None -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "영화 제목으로 검색해 보세요.",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = FilmoColor.txt_02,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            is SearchUiState.Success -> {
                LazyColumn {
                    items(searchMovieUiState.movieList) {
                        MovieItem(movie = it)
                    }
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
            modifier = Modifier.size(100.dp)
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
                    letterSpacing = 0.14.sp
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
                    letterSpacing = 0.12.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun SearchMovieScreenPreview() {
    SearchMovieScreen(searchMovieUiState = SearchUiState.None)
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(movie = Movie(title = "영화 제목", releaseYear = 2023))
}
