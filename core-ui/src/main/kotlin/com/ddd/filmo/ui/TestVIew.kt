package com.ddd.filmo.ui

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.RoundedPolygon
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import de.apuri.physicslayout.lib.PhysicsLayout
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
fun Test2Preview() {
    Surface(shape = CircleShape) {
        PhysicsLayout() {
            Icon(
                modifier = Modifier.padding(18.dp),
                painter = painterResource(id = FilmoIcon.Heart),
                tint = Color.Blue,
                contentDescription = "",
            )
        }
    }
}

@Preview
@Composable
fun Test3Preview() {
    Card() {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = "“ Please, let me keep this memory \n just this moment.”",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = FilmoColor.txt_04,
                textAlign = TextAlign.Center,
            ),
        )
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
