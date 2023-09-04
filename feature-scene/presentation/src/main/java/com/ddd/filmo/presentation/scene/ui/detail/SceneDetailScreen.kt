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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.window.Dialog
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
    val isClicked: Boolean = false,
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
            FilmUi(),

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
            0xFF9CCEFF,
        )
    }
}

@Preview
@Composable
fun SceneDetailScreenPreview() {
    SceneDetailScreen()
}
