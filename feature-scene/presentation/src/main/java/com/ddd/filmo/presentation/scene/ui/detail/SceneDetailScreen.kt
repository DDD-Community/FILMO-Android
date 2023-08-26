package com.ddd.filmo.presentation.scene.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.textfield.FilmoOutlinedTextField
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene
import com.ddd.filmo.presentation.scene.ui.detail.FilmUi.Companion.filmDialogUiList
import com.ddd.filmo.presentation.scene.ui.edit.SceneEditScreen
import com.ddd.filmo.presentation.scene.ui.read.SceneReadScreen
import com.ddd.filmo.ui.FilmBody
import com.ddd.filmo.ui.FilmSize

@Composable
internal fun SceneDetailScreenRoute(
//    viewModel: MainViewModel = hiltViewModel(),
//    navigateToReadVote: (Int) -> Unit,
//    navigateToSearch: () -> Unit,
) {
    SceneDetailScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneDetailScreen(
    scene: Scene? = Scene.mock,
    onBackClick: () -> Unit = {},
) {
    var screenIndex by remember { mutableStateOf(0) }

    val toEditScreen = fun() {
        screenIndex = 1
    }

    val toReadScreen = fun() {
        screenIndex = 0
    }

    scene?.let {
        if (screenIndex == 0) {
            SceneReadScreen(scene, toEditScreen)
        } else {
            SceneEditScreen(scene, toReadScreen)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AddFilmDialog() {
    val filmState = remember {
        filmDialogUiList.toMutableStateList()
    }

    AlertDialog(onDismissRequest = { /*TODO*/ }) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp, bottom = 24.dp),
                text = "필름 추가하기",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(700),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp,
                ),
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "이름 설정",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                    ),
                )
                Text(
                    text = "5/20",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                    ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilmoOutlinedTextField(
                value = "",
                onValueChanged = {},
                placeholderText = "닉네임을 입력해주세요.",
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "필름 컬러 설정",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_02,
                ),

            )
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Fixed(5),
                contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                itemsIndexed(items = filmState.toList()) { idx, it ->
                    Surface(
                        color = Color.Black,
                        onClick = {
                            filmState.replaceAll { it.copy(isClicked = false) }
                            filmState[idx] =
                                filmState[idx].copy(isClicked = !filmState[idx].isClicked)
                        },
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp),
                    ) {
                        FilmBody(
                            color = it.color,
                            filmSize = FilmSize.Small,
                            isClicked = it.isClicked,
                        ) {
                            if (it.isClicked) {
                                Surface(
                                    color = Color(0x1A000000),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .aspectRatio(1f)
                                        .align(Alignment.Center),
                                    shape = CircleShape,
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
                                        painter = painterResource(FilmoIcon.Check),
                                        contentDescription = "",
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Row() {
                FilmoButton(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(48.dp),
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = FilmoColor.txt_03,
                    ),
                    onClick = {
                    },
                ) {
                    Text(
                        text = "취소하기",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.1.sp,
                        ),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilmoButton(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(48.dp),
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = FilmoColor.Primary,
                    ),
                    onClick = {},
                ) {
                    Text(
                        text = "필름 추가하기",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.1.sp,
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SmallFilmCase(modifier: Modifier = Modifier, color: Color, isChoice: Boolean) {
    FilmBody(modifier = modifier, color = color, filmSize = FilmSize.Small) {
    }
}

@Preview
@Composable
fun a() {
    Surface() {
        SmallFilmCase(modifier = Modifier, color = Color.Blue, isChoice = false)
    }
}

data class FilmUi(
    val color: Color,
    val isClicked: Boolean = false,
) {
    companion object {
        val filmDialogUiList = listOf(
            FilmUi(color = FilmoColor.film_color_01),
            FilmUi(color = FilmoColor.film_color_02),
            FilmUi(color = FilmoColor.film_color_03),
            FilmUi(color = FilmoColor.film_color_04),
            FilmUi(color = FilmoColor.film_color_05),
            FilmUi(color = FilmoColor.film_color_06),
            FilmUi(color = FilmoColor.film_color_07),
            FilmUi(color = FilmoColor.film_color_08),
            FilmUi(color = FilmoColor.film_color_09),
            FilmUi(color = FilmoColor.film_color_10),

        )
    }
}
