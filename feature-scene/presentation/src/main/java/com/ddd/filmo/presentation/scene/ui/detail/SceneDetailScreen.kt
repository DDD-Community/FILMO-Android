package com.ddd.filmo.presentation.scene.ui.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.filmo.model.Scene
import com.ddd.filmo.presentation.scene.ui.edit.SceneEditScreen
import com.ddd.filmo.presentation.scene.ui.read.SceneReadScreen
import com.ddd.filmo.ui.FilmBody
import com.ddd.filmo.ui.FilmSize

@Composable
fun SceneDetailScreenRoute(
//    viewModel: MainViewModel = hiltViewModel(),
//    navigateToReadVote: (Int) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToBack: () -> Unit = {}
) {
    SceneDetailScreen(
        onBackButtonClicked = navigateToBack,
        onSearchButtonClicked = navigateToSearch
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneDetailScreen(
    scene: Scene? = Scene.mock,
    onSearchButtonClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit = {}
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
            SceneReadScreen(toEditScreen, onBackButtonClicked = onBackButtonClicked)
        } else {
            SceneEditScreen(scene, onBackButtonClicked = toReadScreen, onSearchButtonClicked = onSearchButtonClicked)
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

@Preview
@Composable
fun SceneDetailScreenPreview() {
    SceneDetailScreen()
}
