package com.ddd.filmo.ui

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.RoundedPolygon
import kotlin.math.min

@Preview
@Composable
fun TestPreview() {
    MaterialTheme {
        val pentagon = RoundedPolygon(5, 200f).apply {
            transform(
                calculateMatrix(bounds = this.bounds, width = 100f, height = 100f),
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                drawPath(
                    pentagon
                        .toPath().asComposePath(),
                    Color.Blue,
                )
                moveTo(100f, -500f)
                drawPath(
                    pentagon
                        .toPath().asComposePath(),
                    Color.Blue,
                )
                close()
            }
            this.drawPath(path, Color.Blue)
        }
    }
}

@Preview
@Composable
fun Test3Preview() {
}

internal fun calculateMatrix(bounds: RectF, width: Float, height: Float): Matrix {
    val originalWidth = bounds.right - bounds.left
    val originalHeight = bounds.bottom - bounds.top
    val scale = min(width / originalWidth, height / originalHeight)
    val newLeft = bounds.left - (width / scale - originalWidth) / 2
    val newTop = bounds.top - (height / scale - originalHeight) / 2
    val matrix = Matrix()
    matrix.setTranslate(-newLeft, -newTop)
    matrix.postScale(scale, scale)
    return matrix
}
