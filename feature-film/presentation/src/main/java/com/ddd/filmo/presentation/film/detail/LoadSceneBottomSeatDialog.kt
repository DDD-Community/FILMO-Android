package com.ddd.filmo.presentation.film.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ddd.filmo.designsystem.component.bottom.FilmoModalBottomSheetDialog
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.checkbox.FilmoCheckBox
import com.ddd.filmo.designsystem.component.textfield.FilmoOutlinedTextField
import com.ddd.filmo.designsystem.component.textfield.FilmoTextFieldLeadingType
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene
import com.ddd.filmo.model.SceneType
import com.ddd.filmo.ui.SceneImageTest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadSceneBottomSeatDialog(
    sheetState: SheetState = rememberModalBottomSheetState(),
    sceneList: List<Scene>,
    onDismissRequest: () -> Unit = {},
    currentScene: Int,
    totalScene: Int,
    checkedScenes: List<Scene> = emptyList(),
    toggleSceneChecked: (Scene) -> Unit = {},
    addCheckedScenes: () -> Unit = {},
) {
    FilmoModalBottomSheetDialog(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState

    ) {
        LoadSceneLayout(
            currentScene,
            totalScene,
            sceneList,
            onBackButtonClicked = onDismissRequest,
            checkedScenes = checkedScenes,
            toggleSceneChecked = toggleSceneChecked,
            addCheckedScenes = addCheckedScenes
        )
    }
}

@Composable
fun LoadSceneDetail(
    modifier: Modifier = Modifier,
    scene: Scene,
    isClicked: Boolean = false,
    onSceneClicked: () -> Unit = {},
    checkedScenes: List<Scene> = emptyList(),
    toggleSceneChecked: (Scene) -> Unit = {}
) {
    val isChecked = checkedScenes.contains(scene)
    Box(
        modifier.clickable {
//            onSceneClicked()
            toggleSceneChecked(scene)
        }.background(
            if (isChecked) Color(0x33553EFF) else FilmoColor.Background3
        )
    ) {
        Column {
            Row(Modifier.height(120.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = scene.sceneText ?: "",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = FilmoColor.txt_01,
                        ),
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = scene.movie?.title ?: "",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 18.2.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = FilmoColor.txt_02
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = Scene.sceneSdf.format(scene.createdAt),
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.8.sp,
                                fontFamily = FilmoFamily,
                                fontWeight = FontWeight(400),
                                color = FilmoColor.txt_02
                            )
                        )
                        /*Image(
                            painter = painterResource(FilmoIcon.Location),
                            contentDescription = ""
                        )
                        Text(
                            text = "기본 필름",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.8.sp,
                                fontFamily = FilmoFamily,
                                fontWeight = FontWeight(400),
                                color = FilmoColor.txt_02
                            )
                        )*/
                    }
                }
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )
                if (scene.imageUrl.isNotEmpty()) {
                    Box(
                        Modifier.height(90.dp).clip(RoundedCornerShape(16.dp))
                    ) {
                        AsyncImage(
                            model = "https://firebasestorage.googleapis.com/v0/b/filmo-698ba.appspot.com/o/" +
                                    scene.imageUrl.replace("/", "%2F") +
                                    "?alt=media",
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(218 / 133f)
                                .background(color = Color(0xff625B71)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
        FilmoCheckBox(
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 10.dp),
            checked = isChecked,
            onCheckedChange = {
//                onSceneClicked()
                toggleSceneChecked(scene)
            }
        )
    }
}

@Composable
fun LoadSceneLayout(
    currentScene: Int,
    totalScene: Int,
    sceneList: List<Scene>,
    onBackButtonClicked: () -> Unit = {},
    checkedScenes: List<Scene> = emptyList(),
    toggleSceneChecked: (Scene) -> Unit = {},
    addCheckedScenes: () -> Unit = {},
) {
    val loadSceneHeaderTextLayoutId = "LoadSceneHeaderTextLayoutId"
    val loadSceneSearchTextFieldLayoutId = "LoadSceneSearchTextFieldLayoutId"
    val loadSceneSearchDetailListLayoutId = "LoadSceneSearchDetailListLayoutId"
    val loadSceneBottomButtonLayoutId = "LoadSceneBottomButtonLayoutId"

    Layout(
        content = {
            Row(
                modifier = Modifier.layoutId(loadSceneHeaderTextLayoutId)
                    .padding(top = 22.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(FilmoColor.txt_01)) {
                            append("씬 가져오기")
                            append(" ${checkedScenes.size}")
                        }
                        withStyle(style = SpanStyle(FilmoColor.txt_02)) {
                            append("/${sceneList.size}")
                        }
                    },
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = FilmoColor.txt_01
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.ddd.filmo.core.designsystem.R.drawable.ic_x),
                        contentDescription = ""
                    )
                }
            }
            FilmoOutlinedTextField(
                modifier = Modifier.layoutId(loadSceneSearchTextFieldLayoutId)
                    .padding(vertical = 8.dp),
                value = "",
                placeholderText = "",
                containerColor = FilmoColor.Background2,
                leadingType = FilmoTextFieldLeadingType.SEARCH
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(Modifier.layoutId(loadSceneSearchDetailListLayoutId)) {
                itemsIndexed(sceneList) { index, item ->
                    LoadSceneDetail(scene = item, onSceneClicked = {}, checkedScenes = checkedScenes, toggleSceneChecked = toggleSceneChecked)
                }
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .layoutId(loadSceneBottomButtonLayoutId),
                contentAlignment = Alignment.Center
            ) {
                val btnColor =
                    if (checkedScenes.isEmpty()) FilmoColor.PrimaryDisabled
                    else FilmoColor.Primary

                FilmoButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onBackButtonClicked()
                        addCheckedScenes()
                    },
                    buttonColors = ButtonDefaults.buttonColors(containerColor = btnColor)
                ) {
                    Text(
                        text = "${checkedScenes.size}개 | 필름에 담기",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = FilmoColor.txt_01,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.16.sp
                        )
                    )
                }
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp)
    ) { measurables, constraints ->

        val looseConstraints = constraints.asLoose()

        val loadSceneHeaderMeasurable =
            measurables.find { it.layoutId == loadSceneHeaderTextLayoutId }
                ?.measure(looseConstraints) ?: throw NullPointerException()

        val loadSceneSearchTextFieldLMeasurable =
            measurables.find { it.layoutId == loadSceneSearchTextFieldLayoutId }
                ?.measure(looseConstraints) ?: throw NullPointerException()

        val loadSceneBottomButtonMeasurable =
            measurables.find { it.layoutId == loadSceneBottomButtonLayoutId }
                ?.measure(looseConstraints) ?: throw NullPointerException()

        val loadSceneSearchDetailListHeight = constraints.maxHeight -
            loadSceneHeaderMeasurable.height -
            loadSceneSearchTextFieldLMeasurable.height -
            loadSceneBottomButtonMeasurable.height

        val loadSceneSearchListConstraints = constraints.copy(
            minHeight = loadSceneSearchDetailListHeight,
            maxHeight = loadSceneSearchDetailListHeight
        )
        val loadSceneSearchListMeasurable =
            measurables.find { it.layoutId == loadSceneSearchDetailListLayoutId }
                ?.measure(loadSceneSearchListConstraints) ?: throw NullPointerException()

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0
            loadSceneHeaderMeasurable.place(0, yPosition)
            yPosition += loadSceneHeaderMeasurable.height
            loadSceneSearchTextFieldLMeasurable.place(0, yPosition)
            yPosition += loadSceneSearchTextFieldLMeasurable.height
            loadSceneBottomButtonMeasurable.place(
                0,
                constraints.maxHeight - loadSceneBottomButtonMeasurable.height
            )
            loadSceneSearchListMeasurable.place(
                0,
                yPosition
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoadSceneBottomSeatDialogPreview() {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LoadSceneBottomSeatDialog(
        sheetState = sheetState,
        currentScene = 0,
        sceneList = SceneImageTest.firstSceneType,
        totalScene = 30
    )
}

@Preview
@Composable
fun LoadSceneLayoutPreview() {
    LoadSceneLayout(
        1,
        30,
        sceneList = SceneImageTest.firstSceneType
    )
}

@Preview
@Composable
fun LoadSceneDetailPreview() {
    LoadSceneDetail(scene = Scene.mock1)
}

@Preview
@Composable
fun LoadSceneNullDetailPreview() {
    LoadSceneDetail(
        scene = Scene.mock1
    )
}

fun Constraints.asLoose(
    height: Boolean = true,
    width: Boolean = false
) = copy(
    minHeight = if (height) 0 else minHeight,
    minWidth = if (width) 0 else minWidth
)
