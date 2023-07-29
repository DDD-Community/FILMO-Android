package com.ddd.filmo.presentation.scene.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ddd.filmo.core.designsystem.R
import com.ddd.filmo.model.Scene
import com.ddd.filmo.presentation.scene.ui.read.SceneReadScreen

@Composable
internal fun SceneDetailScreenRoute(
//    viewModel: MainViewModel = hiltViewModel(),
//    navigateToReadVote: (Int) -> Unit,
//    navigateToSearch: () -> Unit,
) {
    SceneDetailScreen()
}

@Composable
fun SceneDetailScreen(
    scene: Scene? = Scene.mock,
    onBackClick: () -> Unit = {},
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var screenIndex by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize()) {
        Row(Modifier.padding(10.dp).fillMaxWidth()) {
            IconButton(onClick = {
                onBackClick()
            }, modifier = Modifier.size(40.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    isDropDownMenuExpanded = true
                },
                modifier = Modifier.size(40.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_three_dots_horizontal),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                )
                DropdownMenu(
                    expanded = isDropDownMenuExpanded,
                    onDismissRequest = {
                        isDropDownMenuExpanded = false
                    },
                ) {
                    // adding items
                    listOf("씬 수정하기", "씬 삭제하기").forEachIndexed { itemIndex, itemValue ->
                        DropdownMenuItem(
                            onClick = {
                                isDropDownMenuExpanded = false
                                screenIndex = 1
                            },
                            text = { Text(text = itemValue) },
                        )
                    }
                }
            }
        }

        scene?.let {
            SceneReadScreen(scene)
        }
    }
}
