package com.ddd.filmo.presentation.scene.ui.create

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.rating.RatingBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.Film

@Composable
fun SceneCreateScreenRoute(
    viewModel: SceneCreateViewModel = hiltViewModel(),
    navigateToSth: () -> Unit = {},
    navigateToBack: () -> Unit = {},
) {
    val film by viewModel.films.collectAsStateWithLifecycle()
    val selectedFilm = viewModel.selectedFilm.collectAsStateWithLifecycle().value
    val selectedUri = viewModel.selectedUri.collectAsStateWithLifecycle().value
    val sceneText = viewModel.sceneText.collectAsStateWithLifecycle().value
    val movieTitle = viewModel.movieTitle.collectAsStateWithLifecycle().value
    val rating = viewModel.rating.collectAsStateWithLifecycle().value
    val isUploading = viewModel.isUploading.collectAsStateWithLifecycle().value
    SceneCreateScreen(
        filmList = film,
        selectedFilm = selectedFilm,
        selectedUri = selectedUri,
        sceneText = sceneText,
        movieTitle = movieTitle,
        rating = rating,
        isUploading = isUploading,
        navigateToSth = { viewModel.createScene(navigateToSth) },
        navigateToBack = navigateToBack,
        onPhotoSelected = viewModel::setSelectedUri,
        onFilmSelected = viewModel::selectFilm,
        onFilmTitleChanged = viewModel::setMovieTitle,
        onFilmContentChanged = viewModel::setSceneText,
        onRatingChanged = viewModel::setRating,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SceneCreateScreen(
    filmList: List<Film> = emptyList(),
    selectedFilm: Film? = null,
    selectedUri: Uri? = null,
    sceneText: String = "",
    movieTitle: String = "",
    rating: Float = 2.5f,
    isUploading: Boolean = false,
    navigateToSth: () -> Unit = {},
    navigateToBack: () -> Unit = {},
    onPhotoSelected: (Uri?) -> Unit = {},
    onFilmSelected: (Film) -> Unit = {},
    onFilmTitleChanged: (String) -> Unit = {},
    onFilmContentChanged: (String) -> Unit = {},
    onRatingChanged: (Float) -> Unit = {},
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> onPhotoSelected(uri) },
    )

    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val closeSheet = fun() {
        isSheetOpen = false
    }

    Column(
        Modifier.fillMaxSize(),
    ) {
        FilmoAppBar(
            actions = {},
            navigationIcon = {
                IconButton(onClick = navigateToBack) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
            title = "씬 만들기",
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(color = Color(0xFF393939), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 16.dp, end = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = "$movieTitle",
                    onValueChange = onFilmTitleChanged,
                    modifier = Modifier.weight(1f).clickable {

                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                    ),
                )
                IconButton(onClick = {
                    onFilmTitleChanged("")
                }) {
                    Icon(
                        painter = painterResource(id = FilmoIcon.X),
                        contentDescription = "X",
                        modifier = Modifier.size(12.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "별점을 클릭해 주세요.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.padding(horizontal = 71.5.dp)) {
                    RatingBar(
                        value = rating,
                        onValueChange = onRatingChanged,
                    ) {
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(size = 8.dp))
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
                        )
                    }
                    .dashedBorder(
                        strokeWidth = 1.dp,
                        color = FilmoColor.ic_02,
                        cornerRadiusDp = 8.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (selectedUri == null) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Image(
                        painter = painterResource(id = FilmoIcon.PlusWithCircle),
                        contentDescription = "Add",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "이미지를 등록해 주세요. (선택)",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFF4F4F4),
                        ),
                    )
                    Text(
                        text = "10mb 이하",
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 16.8.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFB6B6B6),
                        ),
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                } else {
                    AsyncImage(
                        model = selectedUri,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(13.dp))
                            .aspectRatio(328f / 204f),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(
                value = "$sceneText",
                onValueChange = onFilmContentChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 294.dp)
                    .background(
                        color = Color(0xFF393939),
                        shape = RoundedCornerShape(size = 8.dp),
                    )
                    .padding(16.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4F4),
                ),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 8.dp))
                    .clickable {
                        isSheetOpen = true
                    }
                    .border(
                        width = 1.dp,
                        color = Color(0xFF7D7A7A),
                        shape = RoundedCornerShape(size = 8.dp),
                    )
                    .fillMaxWidth()
                    .height(58.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                if (selectedFilm != null) {
                    Icon(
                        painter = painterResource(id = FilmoIcon.Folder),
                        contentDescription = "image description",
                        tint = Color(selectedFilm.caseColor),
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                if (selectedFilm != null) {
                    Text(
                        text = "위치: ${selectedFilm.name}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFF4F4F4),
                        ),
                        modifier = Modifier.weight(1f),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }

        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Button(
                onClick = { navigateToSth() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FilmoColor.PrimaryVariant,
                    contentColor = Color(0xFFF4F4F4),
                ),
                shape = RoundedCornerShape(size = 8.dp),
            ) {
                Text(
                    text = "씬 만들기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFF4F4F4),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.16.sp,
                    ),
                )
            }
        }
    }

    if (isSheetOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x40000000)),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable { isSheetOpen = false },
            )
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
//                    .fillMaxSize()
                    .background(Color(0xFF393939))
                    .align(Alignment.BottomCenter),
            ) {
                Spacer(modifier = Modifier.size(22.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
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
                        modifier = Modifier.weight(1f),
                    )
                    IconButton(
                        onClick = {
                            isSheetOpen = false
                        },
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

                for (film in filmList) {
                    FilmListItem(
                        film,
                        selectedFilm,
                        selectFilm = { onFilmSelected(it) },
                        closeSheet,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    if (isUploading) {
        BasicAlertDialog(
            onDismissRequest = {},
            content = {
                Text(
                    text = "씬을 업로드하는 중입니다.\n잠시만 기다려주세요.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
}

fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx),
                    )
                }
            },
        )
    },
)

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        onClick()
    }
}

@Composable
fun FilmListItem(
    film: Film,
    selectedFilm: Film?,
    selectFilm: (Film) -> Unit = {},
    closeSheet: () -> Unit,
) {
    val selected = (film == selectedFilm)
    val borderOrNot =
        if (selected) {
            Modifier.border(
                width = 2.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 8.dp),
            )
        } else {
            Modifier
        }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .then(borderOrNot)
            .clickable {
                selectFilm(film)
                closeSheet()
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(32.dp)
                .background(Color(film.caseColor)),
        )
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
            modifier = Modifier.weight(1f),
        )
        if (selected) {
            Icon(
                painter = painterResource(FilmoIcon.Check),
                contentDescription = "",
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

@Preview
@Composable
fun SceneCreateScreenPreview() {
    SceneCreateScreen()
}
