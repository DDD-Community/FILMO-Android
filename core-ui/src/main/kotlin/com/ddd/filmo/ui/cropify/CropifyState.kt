package com.ddd.filmo.ui.cropify

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

@Composable
fun rememberCropifyState() = remember { CropifyState() }

@Stable
class CropifyState {
    internal var frameRect by mutableStateOf(Rect(-100f, -100f, 100f, 100f))
    internal var imageRect by mutableStateOf(Rect(0f, 0f, 0f, 0f))
    internal var loadedUri: Uri? = null
    internal var inSampleSize = 1

    /**
     * 영역 안을 클릭하면 Offset 이동이 됨.
     *
     * @param offset
     */
    internal fun translateFrameRect(offset: Offset) {
        frameRect = frameRect.translate(offset)
    }

    internal fun scaleFrameRect(
        point: TouchRegion.Vertex,
//        aspectRatio: AspectRatio?,
        amount: Offset,
        minimumVertexDistance: Float,
    ) {
        frameRect = scaleFlexibleRect(
            point = point,
            amount = amount,
            minimumVertexDistance = minimumVertexDistance,
        )

        /*if (aspectRatio == null) {
            scaleFlexibleRect(point, amount, minimumVertexDistance)
        } else {
            scaleFixedAspectRatioRect(point, amount, minimumVertexDistance)
        }*/
    }

    private fun scaleFlexibleRect(
        point: TouchRegion.Vertex,
        amount: Offset,
        minimumVertexDistance: Float,
    ): Rect {
        return frameRect.run {
            when (point) {
                TouchRegion.Vertex.TOP_LEFT -> Rect(
                    left = left + amount.x,
                    top = top + amount.y,
                    right = right,
                    bottom = bottom,
                )

                TouchRegion.Vertex.TOP_RIGHT -> Rect(
                    left = left,
                    top = top + amount.y,
                    right = right + amount.x,
                    bottom = bottom,
                )

                TouchRegion.Vertex.BOTTOM_LEFT -> Rect(
                    left = left + amount.x,
                    top = top,
                    right = right,
                    bottom = bottom + amount.y,
                )

                TouchRegion.Vertex.BOTTOM_RIGHT -> Rect(
                    left = left,
                    top = top,
                    right = right + amount.x,
                    bottom = bottom + amount.y,
                )
            }
        }
    }
}
