package com.ddd.filmo.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.window.Dialog
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.text.FilmoAutoResizeText
import com.ddd.filmo.designsystem.component.text.FontSizeRange
import com.ddd.filmo.designsystem.component.textfield.FilmoOutlinedTextField
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.ui.FilmUi.Companion.filmColorLongList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFilmDialog(
    onDismissRequest: () -> Unit = {},
    createFilm: (String, Long) -> Unit = { _, _ -> },
    onCancelButtonClicked: () -> Unit = {},
) {
    val filmState = remember {
        FilmUi.filmDialogUiList.toMutableStateList()
    }

    var filmName by remember { mutableStateOf("") }

    val isFilmNameLength by remember { derivedStateOf { filmName.length < 20 } }

    var filmColor by remember { mutableLongStateOf(0xFF9868FF) }

    val createFilmButtonEnabled by remember {
        derivedStateOf {
            isFilmNameLength && filmState.any { it.isClicked } && filmName.isNotBlank()
        }
    }
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            color = FilmoColor.Background3,
            shape = RoundedCornerShape(12.dp),

        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
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
                        text = "${filmName.length}/20",
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
                    value = filmName,
                    onValueChanged = { filmName = it },
                    placeholderText = "닉네임을 입력해주세요.",
                    isError = !isFilmNameLength,
                    containerColor = FilmoColor.Background2,
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
                            color = FilmoColor.Background2,
                            onClick = {
                                filmState.replaceAll { it.copy(isClicked = false) }
                                filmState[idx] =
                                    filmState[idx].copy(isClicked = !filmState[idx].isClicked)
                                filmColor = filmColorLongList[idx]
                            },
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp),
                        ) {
                            FilmBody(
                                color = Color(filmColorLongList[idx]),
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
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(vertical = 5.dp),
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
                        onClick = onCancelButtonClicked,
                    ) {
                        FilmoAutoResizeText(
                            text = "취소하기",
                            fontSizeRange = FontSizeRange(12.sp, 16.sp),
                            color = FilmoColor.txt_01,
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
                            contentColor = FilmoColor.txt_01,
                            disabledContentColor = FilmoColor.txt_02,
                            disabledContainerColor = FilmoColor.PrimaryDisabled,
                        ),
                        enabled = createFilmButtonEnabled,
                        onClick = {
                            createFilm(filmName, filmColor)
                        },
                    ) {
                        FilmoAutoResizeText(
                            text = "필름 추가하기",
                            fontSizeRange = FontSizeRange(12.sp, 16.sp),
                            color = FilmoColor.txt_01,
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
}

@Preview
@Composable
fun AddFilmDialogPreview() {
    AddFilmDialog()
}

data class FilmUi(
    val isClicked: Boolean = false
) {
    companion object {
        val filmDialogUiList = listOf(
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi(),
            FilmUi()

        )

        val filmColorLongList = listOf(
            0xFF9868FF,
            0xFFCF68FF,
            0xFFFF97CA,
            0xFFFF5C5D,
            0xFFFF5C5D,
            0xFFF5DF1A,
            0xFFFFCE4F,
            0xFF1FCF6A,
            0xFFBBEF4C,
            0xFF9CCEFF
        )
    }
}