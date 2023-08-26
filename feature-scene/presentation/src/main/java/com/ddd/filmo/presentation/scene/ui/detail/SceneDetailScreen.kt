package com.ddd.filmo.presentation.scene.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film
import com.ddd.filmo.model.Scene
import com.ddd.filmo.presentation.scene.ui.edit.SceneEditScreen
import com.ddd.filmo.presentation.scene.ui.read.SceneReadScreen
import com.ddd.filmo.ui.SceneImage

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

