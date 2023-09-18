package com.ddd.filmo.ui.cropify

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImageOverlay(
    size: Size = Size(500f, 500f),
    offset: Offset = Offset(0f, 0f),
) {
    Canvas(Modifier.fillMaxSize()) {
//        with(this.drawContext.canvas.nativeCanvas) {}
        drawFrame(size, offset)
        drawGrid(size, offset)
        drawCorner(size, offset)
    }
}

/**
 * Crop의 격자 프레임을 만드는 함수
 *
 * @param size
 * @param offset - 격자 프레임의 position
 */
fun DrawScope.drawFrame(size: Size, offset: Offset) {
    drawRect(
        color = Color.White,
        topLeft = offset,
        size = size,
        style = Stroke(3f),
    )
}

/**
 * Crop의 줄을 긋는 함수
 *
 * @param size
 */
fun DrawScope.drawGrid(size: Size, offset: Offset) {
    // 2d는 rect를 쓰면  쉽게 사용할 수 있다.
    val rect = Rect(offset, size)
    val oneOfThirdWidth = size.width / 3
    val oneOfThirdHeight = size.height / 3
    drawLine(
        color = Color.White,
        start = rect.topLeft.translateX(oneOfThirdWidth),
        end = rect.bottomLeft.translateX(oneOfThirdWidth),
        strokeWidth = 3f,
    )

    drawLine(
        color = Color.White,
        start = rect.topLeft.translateY(oneOfThirdHeight),
        end = rect.topRight.translateY(oneOfThirdHeight),
        strokeWidth = 3f,
    )

    val twoOfThirdWidth = oneOfThirdWidth * 2
    val twoOfThirdHeight = oneOfThirdHeight * 2
    drawLine(
        color = Color.White,
        start = rect.topLeft.translateX(twoOfThirdWidth),
        end = rect.bottomLeft.translateX(twoOfThirdWidth),
        strokeWidth = 3f,
    )

    drawLine(
        color = Color.White,
        start = rect.topLeft.translateY(twoOfThirdHeight),
        end = rect.topRight.translateY(twoOfThirdHeight),
        strokeWidth = 3f,
    )
}

fun DrawScope.drawCorner(size: Size, offset: Offset) {
    val rect = Rect(offset, size)

    rect.topLeft.transLateXY(10f, 10f).let { start ->
        drawLine(
            start = start,
            end = start.transLateXY(50f, 0f),
            color = Color.White,
            strokeWidth = 3f,
        )
        drawLine(
            start = start,
            end = start.transLateXY(0f, -50f),
            color = Color.White,
            strokeWidth = 3f,
        )
    }

    rect.bottomLeft.transLateXY(10f, -10f).let { start ->
        drawLine(
            start = start,
            end = start.transLateXY(50f, 0f),
            color = Color.White,
            strokeWidth = 3f,
        )
        drawLine(
            start = start,
            end = start + Offset(0f, -50f),
            color = Color.White,
            strokeWidth = 3f,
        )
    }

    rect.topRight.transLateXY(-10f, 10f).let { start ->
        drawLine(
            start = start,
            end = start.transLateXY(-50f, 0f),
            color = Color.White,
            strokeWidth = 3f,
        )
        drawLine(
            start = start,
            end = start + Offset(0f, 50f),
            color = Color.White,
            strokeWidth = 3f,
        )
    }

    rect.bottomRight.transLateXY(-10f, -10f).let { start ->
        drawLine(
            start = start,
            end = start.transLateXY(-50f, 0f),
            color = Color.White,
            strokeWidth = 3f,
        )
        drawLine(
            start = start,
            end = start + Offset(0f, -50f),
            color = Color.White,
            strokeWidth = 3f,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageOverlayPreview() {
    Surface(color = Color.Black) {
        ImageOverlay()
    }
}

internal fun Offset.transLateXY(x: Float, y: Float) = this + Offset(x, y)
internal fun Offset.translateX(amount: Float) = copy(x = x + amount)
internal fun Offset.translateY(amount: Float) = copy(y = y + amount)
