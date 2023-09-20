package com.ddd.filmo.ui.cropify

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Cropify(
    uri: Uri,
    modifier: Modifier = Modifier,
    onFailedToLoadImage: (Throwable) -> Unit,
) {
    var sampledImageBitmap by remember(uri) { mutableStateOf<SampledImageBitmap?>(null) }

    val density = LocalDensity.current
    val context = LocalContext.current
    var state = rememberCropifyState()
    var touchRegion = remember<TouchRegion?> { null }

    BoxWithConstraints(
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset ->
                    Log.d("Cropify", "onDragStart: $offset")
                    touchRegion = detectTouchRegion(
                        tapPosition = offset,
                        frameRect = state.frameRect,
                        tolerance = 100f,
                    )
                },
                onDragEnd = {
                    Log.d("Cropify", "onDragEnd")
                    touchRegion = null
                },
                onDrag = { change, dragAmount ->
                    touchRegion?.let {
                        when (it) {
                            is TouchRegion.Vertex -> state.scaleFrameRect(
                                it,
                                dragAmount,
                                50f * density.density,
                            )

                            TouchRegion.Inside -> state.translateFrameRect(dragAmount)
                        }
                        change.consume()
                    }
                },
            )
        },
    ) {
        LaunchedEffect(uri) {
            try {
                sampledImageBitmap = loadSampledBitmap(
                    context,
                    uri,
                    constraints.run { IntSize(maxWidth, maxHeight) },
                )
                state.loadedUri = uri
                state.inSampleSize = requireNotNull(sampledImageBitmap).inSampleSize

                // uri가 바뀔때 재계산
                val canvasSize =
                    Size(constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat())
                state.imageRect =
                    calculateImagePosition(sampledImageBitmap!!.imageBitmap, canvasSize)
            } catch (throwable: Throwable) {
                sampledImageBitmap = null
                onFailedToLoadImage(throwable)
            }
        }
        ImageOverlay(state.frameRect.size, state.frameRect.topLeft)
        if (sampledImageBitmap != null) {
            ImageCanvas(
                modifier = Modifier.matchParentSize(),
                bitmap = sampledImageBitmap!!.imageBitmap,
                offset = state.imageRect.topLeft,
                size = state.imageRect.size,
            )
        }
    }
}

/**
 * 터치한 범위를 기준으로 어디 영역인지 확인하는 함수
 *
 * @param tapPosition 클릭한 위치
 * @param frameRect 현재 격자
 * @param tolerance 터치 허용 범위 (원형태로 그려짐.)
 * @return
 */
internal fun detectTouchRegion(
    tapPosition: Offset,
    frameRect: Rect,
    tolerance: Float,
): TouchRegion? {
    return when {
        Rect(frameRect.topLeft, tolerance).contains(tapPosition) -> TouchRegion.Vertex.TOP_LEFT
        Rect(frameRect.topRight, tolerance).contains(tapPosition) -> TouchRegion.Vertex.TOP_RIGHT
        Rect(
            frameRect.bottomLeft,
            tolerance,
        ).contains(tapPosition) -> TouchRegion.Vertex.BOTTOM_LEFT

        Rect(
            frameRect.bottomRight,
            tolerance,
        ).contains(tapPosition) -> TouchRegion.Vertex.BOTTOM_RIGHT

        Rect(
            frameRect.center,
            frameRect.width / 2 - tolerance,
        ).contains(tapPosition) -> TouchRegion.Inside

        else -> null
    }
}

internal fun calculateImagePosition(bitmap: ImageBitmap, canvasSize: Size): Rect {
    val imageSize = calculateImageSize(bitmap, canvasSize)
    return Rect(
        Offset(
            (canvasSize.width - imageSize.width) / 2,
            (canvasSize.height - imageSize.height) / 2,
        ),
        imageSize,
    )
}

internal fun calculateImageSize(bitmap: ImageBitmap, canvasSize: Size): Size {
    val newSize = Size(canvasSize.width, canvasSize.width * bitmap.height / bitmap.width.toFloat())
    return if (newSize.height > canvasSize.height) {
        (canvasSize.height / newSize.height).let { Size(newSize.width * it, newSize.height * it) }
    } else {
        newSize
    }
}

@Preview
@Composable
fun CropifySamplePreview() {
    Cropify(
        uri = Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzhmZXqPj6FgycyoV1cKh502RRGouDtO65SQ&usqp=CAU"),
        modifier = Modifier.background(Color.Black),
        onFailedToLoadImage = {}
    )
}
