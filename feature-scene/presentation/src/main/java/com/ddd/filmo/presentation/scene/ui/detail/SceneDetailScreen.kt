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
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var screenIndex by remember { mutableStateOf(0) }
    var selectedFilm by remember { mutableStateOf<Film?>(null) }

    val selectFilm = fun (film: Film) {
        selectedFilm = film
    }

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
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
            if (screenIndex == 0) SceneReadScreen(scene) else SceneEditScreen(scene)
        }
    }

    ModalBottomSheet(
        onDismissRequest = {},
        modifier = Modifier.fillMaxWidth(),
        sheetState = rememberModalBottomSheetState(),
        shape = BottomSheetDefaults.ExpandedShape,
        containerColor = BottomSheetDefaults.ContainerColor,
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            ) {
                Text(
                    text = "필름 선택하기",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFF4F4F4),
                    ),
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(40.dp),
                ) {
                    Icon(
                        painter = painterResource(FilmoIcon.X),
                        contentDescription = "",
                        modifier = Modifier.size(14.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            for (film in Film.fakeFilmList) {
                FilmListItem(film, selectedFilm, selectFilm)
            }
        }
    }
}

@Composable
fun FilmListItem(film: Film, selectedFilm: Film?, selectFilm: (Film) -> Unit = {}) {
    val selected = (film == selectedFilm)
    val borderOrNot =
        if (selected) Modifier.border(width = 2.dp, color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
        else Modifier

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .then(borderOrNot)
            .clickable {
                selectFilm(film)
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .size(32.dp)
            .background(Color(film.caseColor)))
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "${film.name}",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(500),
                color = Color(0xFFF4F4F4),
                letterSpacing = 0.14.sp,
            ),
            modifier = Modifier.weight(1f)
        )
        if(selected) {
            Icon(
                painter = painterResource(FilmoIcon.Check),
                contentDescription = "",
                modifier = Modifier.size(32.dp),
            )
        }
    }
}