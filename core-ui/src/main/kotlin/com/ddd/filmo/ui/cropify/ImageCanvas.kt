package com.ddd.filmo.ui.cropify

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

/**
 * Canvas로 이미지 그리는 함수
 *
 * @param bitmap
 * @param offset
 * @param size
 * @param modifier
 */
@Composable
fun ImageCanvas(
    bitmap: ImageBitmap,
    offset: Offset,
    size: Size,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        drawImage(
            image = bitmap,
            dstSize = size.toInt(),
            dstOffset = offset.toInt(),
        )
    }
}

internal fun Size.toInt() = IntSize(width.toInt(), height.toInt())
internal fun Offset.toInt() = IntOffset(this.x.toInt(), this.y.toInt())
