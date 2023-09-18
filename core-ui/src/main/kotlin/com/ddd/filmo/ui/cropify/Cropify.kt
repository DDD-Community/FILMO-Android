package com.ddd.filmo.ui.cropify

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun Cropify(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    var state = rememberCropifyState()
//    val frameSize = Size(500f, 500f)
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
        ImageOverlay(state.frameRect.size, state.frameRect.topLeft)
//        ImageCanvas()
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

@Preview
@Composable
fun CropifySamplePreview() {
    Cropify(modifier = Modifier.background(Color.Black))
}
