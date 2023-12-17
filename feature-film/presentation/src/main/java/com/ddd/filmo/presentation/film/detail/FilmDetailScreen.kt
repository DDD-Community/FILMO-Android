package com.ddd.filmo.presentation.film.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.bottom.FilmoChoiceBottomSheetDialog
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.dialog.FilmoDialog
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.Scene
import com.ddd.filmo.ui.UpdateFilmDialog
import com.ddd.filmo.ui.SceneImage
import com.ddd.filmo.ui.SceneImageTest
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreenRoute(
    toAddScene: (Scene?) -> Unit = {},
    onBackClick: () -> Unit = {},
    navigateToSceneDetail: () -> Unit = {},
    viewModel: FilmDetailViewModel = hiltViewModel(),
) {
    val selectedFilm = viewModel.selectedFilm.collectAsStateWithLifecycle().value
    val selectedFilmScenes = viewModel.selectedFilmScenes.collectAsStateWithLifecycle().value
    var isSceneDialogState by remember { mutableStateOf(false) }
    var isLoadSceneDialogState by remember { mutableStateOf(false) }
    var isDeleteDialogState by remember { mutableStateOf(false) }
    val isEditDialogState = viewModel.isEditDialogState.collectAsStateWithLifecycle().value

    if (isEditDialogState) {
        UpdateFilmDialog(
            onDismissRequest = { viewModel.setIsEditDialogState(false) },
            viewModel::updateFilm,
            onCancelButtonClicked = { viewModel.setIsEditDialogState(false) },
            selectedFilm.name
        )
    }

    if (isSceneDialogState) {
        FilmoChoiceBottomSheetDialog(
            onDismissRequest = { isSceneDialogState = false },
            choiceList = listOf("필름 수정하기", "필름 삭제하기", "취소하기"),
            onItemClicked = {
                when (it) {
                    0 -> viewModel.setIsEditDialogState(true)
                    1 -> isDeleteDialogState = true
                    2 -> Unit
                }
                isSceneDialogState = false
            },
        )
    }
    if (isLoadSceneDialogState) {
        LoadSceneBottomSeatDialog(
            onDismissRequest = { isLoadSceneDialogState = false },
            currentScene = 10,
            totalScene = 20,
            sceneList = SceneImageTest.testSceneType,
        )
    }

    if (isDeleteDialogState) {
        FilmDeleteDialog(
            onAcceptClicked = {
                isDeleteDialogState = false
                viewModel.deleteFilm()
                onBackClick() },
            onCancelClicked = { isDeleteDialogState = false },
        )
    }

    FilmDetailScreen(
        toAddScene,
        onBackClick,
        onPlusButtonClicked = {
            isSceneDialogState = true
        },
        onLoadSceneButtonClicked = {
            isLoadSceneDialogState = true
        },
        navigateToSceneDetail,
        selectScene = viewModel::selectScene,
        selectedFilm,
        selectedFilmScenes,

    )
}

@Composable
fun FilmDetailScreen(
    toAddScene: (Scene?) -> Unit = {},
    onBackClick: () -> Unit = {},
    onPlusButtonClicked: () -> Unit = {},
    onLoadSceneButtonClicked: () -> Unit = {},
    navigateToSceneDetail: () -> Unit = {},
    selectScene: (Scene) -> Unit = {},
    selectedFilm: Film,
    selectedFilmScenes: List<Scene>,
) {
    val sdf = java.text.SimpleDateFormat("yy.MM.dd")
    val date = sdf.format(Date())

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff202020)),
    ) {
        FilmoAppBar(
            actions = {
                IconButton(onClick = onPlusButtonClicked, modifier = Modifier.size(40.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_three_dots_horizontal),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp),
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
        )

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
                    ),
            )
            if (selectedFilmScenes.isEmpty()) {
                Column(
                    modifier = Modifier.align(Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "씬을 가져와 \n비어있는 필름을 채워주세요",
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 30.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = FilmoColor.txt_05,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    Spacer(Modifier.height(16.dp))
                    FilmoButton(
                        buttonColors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFECECEC),
                            contentColor = FilmoColor.ic_03,
                        ),
                        roundedCornerShape = RoundedCornerShape(100.dp),
                        contentPadding = PaddingValues(horizontal = 60.dp),
                        onClick = {
                            onLoadSceneButtonClicked()
                        },
                    ) {
                        Text(
                            text = "씬 가져오기",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                fontFamily = FilmoFamily,
                                fontWeight = FontWeight(500),
                                color = FilmoColor.ic_03,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.1.sp,
                            ),
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = modifier,
                    content = {
                        item {
                            Spacer(modifier = Modifier.size(104.dp))
                        }
                        selectedFilmScenes.forEach {
                            item {
                                SceneImage(
                                    scene = it,
                                    navigateToSceneDetail = {
                                        selectScene(it)
                                        navigateToSceneDetail()
                                    },
                                )
                            }
                        }
                    },
                )
            }
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
                    text = selectedFilm.scenes.size.toString(),
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

            FloatingActionButton(
                onClick = {
                    toAddScene(null)
                },
                containerColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier
                    .align(BottomEnd)
                    .padding(end = 22.dp, bottom = 24.dp),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        }
    }
}

@Composable
internal fun FilmDeleteDialog(onAcceptClicked: () -> Unit = {}, onCancelClicked: () -> Unit = {}) {
    FilmoDialog(
        onAcceptClicked = onAcceptClicked,
        onCancelClicked = onCancelClicked,
        cancelText = "취소하기",
        acceptText = "삭제하기",
        cancelColors = ButtonDefaults.buttonColors(
            containerColor = FilmoColor.txt_03,
        ),
        acceptColors = ButtonDefaults.buttonColors(
            containerColor = FilmoColor.film_color_05,
        ),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "필름 삭제하기",
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "필름 삭제 시 기록을 복구 할 수 없으며, \n" +
                    "필름 내에 있는 씬도 전부 삭제됩니다.\n" +
                    "정말 삭제하시겠어요?",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.16.sp,
                ),
            )
        }
    }
}

@Preview
@Composable
fun FilmDetailScreenPreview() {
    FilmDetailScreen(
        toAddScene = {},
        onBackClick = {},
        navigateToSceneDetail = {},
        selectedFilm = Film.fakeFilm0,
        selectedFilmScenes = listOf(Scene.mock),
    )
}

@Preview
@Composable
fun FilmDeleteDialogPreview() {
    FilmDeleteDialog()
}
