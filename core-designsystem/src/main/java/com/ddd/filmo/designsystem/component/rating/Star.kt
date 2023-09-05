package com.ddd.filmo.designsystem.component.rating

import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import com.ddd.filmo.designsystem.theme.FilmoColor
import kotlin.math.min

/**
 * A star shape that can be drawn on a canvas.
 * @param fraction  0.0f - 1.0f (0% - 100%)
 * @param isRtl  true - false (right to left)
 */

@Composable
internal fun RatingStar(
    @FloatRange(from = 0.0, to = 1.0) fraction: Float,
    modifier: Modifier = Modifier,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    Box(modifier = modifier) {
        EmptyStar(fraction, isRtl)
        FiledStar(
            fraction,
            isRtl,
        )
    }
}

@Composable
internal fun RatingStars(
    width: Float,
    numOfStars: Int = 5,
    value: Float = 0.0f,
) {
    val ratingPerStar = 1f
    var remainingRating = value
    Row(
        modifier = Modifier,
    ) {
        for (i in 1..numOfStars) {
            val starRating = when {
                value == 0f -> {
                    0f
                }

                remainingRating >= ratingPerStar -> {
                    remainingRating -= ratingPerStar
                    1f
                }

                else -> {
                    val fraction = remainingRating / ratingPerStar
                    remainingRating = 0f
                    fraction
                }
            }
            RatingStar(fraction = starRating, modifier = Modifier.width(width.dp).height(50.dp))
        }
    }
}

@Composable
internal fun FiledStar(
    fraction: Float = 0.5f,
    isRtl: Boolean = false,
//    painterFilled: Painter?,
) {
    Canvas(
        Modifier.fillMaxSize().clip(
            if (isRtl) {
                rtlFilledStarFractionalShape(fraction = fraction)
            } else {
                FractionalRectangleShape(0f, fraction)
            },
        ),
    ) {
        val width = size.width
        val height = size.height
        Log.d("Star", "width: $width, height: $height")

        val path = Path()
        val star = RoundedPolygon
            .star(
                5,
                radius = width / 2,
                innerRadius = width / 4.5f,
                rounding = CornerRounding(radius = width / 64),
                center = PointF(width / 2, height / 2),
                innerRounding = CornerRounding(radius = width / 32),

            ).apply {
                transform(
                    Matrix().apply {
                        postRotate(55f, width / 2, height / 2)
                    },
                )
            }.toPath()

        path.addPath(star.asComposePath())
        this.drawPath(path, color = FilmoColor.Primary)
    }
}

@Composable
internal fun EmptyStar(
    fraction: Float = 0.5f,
    isRtl: Boolean = false,
) {
    Canvas(Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        Log.d("EmptyStar", "width: $width, height: $height")
        val path = Path()
        val star = RoundedPolygon
            .star(
                5,
                radius = width / 2,
                innerRadius = width / 4.5f,
                rounding = CornerRounding(radius = width / 64),
                center = PointF(width / 2, height / 2),
                innerRounding = CornerRounding(radius = width / 32),

            ).apply {
                transform(
                    Matrix().apply {
                        postRotate(55f, width / 2, height / 2)
                    },
                )
            }.toPath()

        path.addPath(star.asComposePath())
        this.drawPath(path, color = FilmoColor.ic_02)
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

internal fun rtlFilledStarFractionalShape(fraction: Float): FractionalRectangleShape {
    return if (fraction == 0f || fraction == 1f) {
        FractionalRectangleShape(0f, fraction)
    } else {
        FractionalRectangleShape(1f - fraction, 1f)
    }
}

@Stable
class FractionalRectangleShape(
    @FloatRange(from = 0.0, to = 1.0) private val startFraction: Float,
    @FloatRange(from = 0.0, to = 1.0) private val endFraction: Float,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Rectangle(
            Rect(
                left = (startFraction * size.width).coerceAtMost(size.width - 1f),
                top = 0f,
                right = (endFraction * size.width).coerceAtLeast(1f),
                bottom = size.height,
            ),
        )
    }
}

@Preview
@Composable
fun StarPreview() {
    FiledStar()
}

@Preview
@Composable
fun EmptyStarPreview() {
    EmptyStar()
}

@Preview
@Composable
fun RatingStarPreview() {
    RatingStar(fraction = 0.5f)
}

@Preview
@Composable
fun RatingStarsPreview() {
    RatingStars(width = 30f, value = 3.5f)
}
