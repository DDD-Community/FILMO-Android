package com.ddd.filmo.presentation.film.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Scene
import com.ddd.filmo.model.SceneType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadSceneBottomSeatDialog(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit = {},
    currentScene: Int,
    totalScene: Int,
) {
    val statusBarHeight = WindowInsets.statusBars.getTop(LocalDensity.current)
    FilmoModalBottomSheetDialog(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,

    ) {
        LoadSceneLayout(currentScene, totalScene)
    }
}

@Composable
fun LoadSceneDetail(scene: Scene) {
    Box {
        Column {
            // todo IntrinsicSize를 넣으니 해결되었다.. 왜?
            Row(Modifier.height(IntrinsicSize.Max)) {
                Column(Modifier.weight(0.6f)) {
                    Text(
                        text = "이 영화는 내가 봤던 영화 중 정말 기억에 남는 영화였다. 결...",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = FilmoColor.txt_01,
                        ),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "어바웃타임",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 18.2.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = FilmoColor.txt_02,
                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = "23.06.08",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.8.sp,
                                fontFamily = FilmoFamily,
                                fontWeight = FontWeight(400),
                                color = FilmoColor.txt_02,
                            ),
                        )
                        Image(
                            painter = painterResource(FilmoIcon.Location),
                            contentDescription = "",
                        )
                        Text(
                            text = "기본 필름",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 16.8.sp,
                                fontFamily = FilmoFamily,
                                fontWeight = FontWeight(400),
                                color = FilmoColor.txt_02,
                            ),
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                        .fillMaxHeight(),
                )
                when (scene.sceneType) {
                    is SceneType.ImageUrl -> {
                        Box(
                            Modifier
                                .weight(0.4f)
                                .fillMaxHeight(),
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data((scene.sceneType as SceneType.ImageUrl).imageUrl)
//                                .placeholder(com.ddd.filmo.core.ui.R.drawable.image_16)
                                    .build(),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }

                    else -> {
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        FilmoCheckBox(
            modifier = Modifier.align(Alignment.TopEnd),
            checked = false,
            onCheckedChange = {},
        )
    }
}

@Composable
fun LoadSceneLayout(currentScene: Int, totalScene: Int) {
    val loadSceneHeaderTextLayoutId = "LoadSceneHeaderTextLayoutId"
    val loadSceneSearchTextFieldLayoutId = "LoadSceneSearchTextFieldLayoutId"
    val loadSceneSearchDetailListLayoutId = "LoadSceneSearchDetailListLayoutId"
    val loadSceneBottomButtonLayoutId = "LoadSceneBottomButtonLayoutId"

    Layout(
        content = {
            Row(
                modifier = Modifier.layoutId(loadSceneHeaderTextLayoutId)
                    .padding(top = 22.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(FilmoColor.txt_01)) {
                            append("씬 가져오기")
                            append(" $currentScene")
                        }
                        withStyle(style = SpanStyle(FilmoColor.txt_02)) {
                            append("/$totalScene")
                        }
                    },
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = FilmoColor.txt_01,
                    ),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.ddd.filmo.core.designsystem.R.drawable.ic_x),
                        contentDescription = "",
                    )
                }
            }
            FilmoOutlinedTextField(
                modifier = Modifier.layoutId(loadSceneSearchTextFieldLayoutId)
                    .padding(vertical = 8.dp),
                value = "",
                placeholderText = "",
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(Modifier.layoutId(loadSceneSearchDetailListLayoutId)) {
                items(10) {
                    LoadSceneDetail(Scene.mock1)
                }
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .layoutId(loadSceneBottomButtonLayoutId),
                contentAlignment = Alignment.Center,
            ) {
                FilmoButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "23개 | 필름에 담기",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = FilmoColor.txt_01,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.16.sp,
                        ),
                    )
                }
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp),
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
            maxHeight = loadSceneSearchDetailListHeight,
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
                constraints.maxHeight - loadSceneBottomButtonMeasurable.height,
            )
            loadSceneSearchListMeasurable.place(
                0,
                yPosition,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LoadSceneBottomSeatDialogPreview() {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LoadSceneBottomSeatDialog(sheetState = sheetState, currentScene = 0, totalScene = 30)
}

@Preview
@Composable
fun LoadSceneLayoutPreview() {
    LoadSceneLayout(1, 30)
}

@Preview
@Composable
fun LoadSceneDetailPreview() {
    LoadSceneDetail(Scene.mock1)
}

@Preview
@Composable
fun LoadSceneNullDetailPreview() {
    LoadSceneDetail(
        Scene("0", "", SceneType.fromColor(1L)),
    )
}

fun Constraints.asLoose(
    height: Boolean = true,
    width: Boolean = false,
) = copy(
    minHeight = if (height) 0 else minHeight,
    minWidth = if (width) 0 else minWidth,
)
