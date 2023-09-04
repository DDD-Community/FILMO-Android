package com.ddd.filmo.designsystem.component.rating

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import kotlin.math.min

@Composable
fun Star() {
    Canvas(Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val path = Path()
        val star = RoundedPolygon
            .star(5, radius = 100f, 50f, center = PointF(width / 2, height / 2)).apply {
                transform(
                    Matrix().apply {
                        postRotate(1f)
                    },
                )
            }.toPath()

        path.addPath(star.asComposePath())
        this.drawPath(path, color = Color.Red)
    }
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

@Preview
@Composable
fun StarPreview() {
    Star()
}
