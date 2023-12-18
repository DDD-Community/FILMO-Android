package com.ddd.filmo.designsystem.component.rating

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    value: Float,
    modifier: Modifier = Modifier,
    numOfStars: Int = 5,
    spaceBetween: Dp = 0.dp,
    isIndicator: Boolean = false,
    hideInactiveStars: Boolean = false,
    onValueChange: (Float) -> Unit,
    onRatingChanged: (Float) -> Unit,
) {
    BoxWithConstraints(modifier.fillMaxSize()) {
        var rowSize by remember { mutableStateOf(Size.Zero) }
        var lastDraggedValue by remember { mutableFloatStateOf(0f) }
        val direction = LocalLayoutDirection.current
        val density = LocalDensity.current

        val paddingInPx = remember {
            with(density) { spaceBetween.toPx() }
        }
        val width = (maxWidth / numOfStars).value
        val widthPx = with(density) {
            width.dp.toPx()
        }

        Row(
            modifier = Modifier
                .onSizeChanged { rowSize = it.toSize() }
                .pointerInput(
                    Unit,
                ) {
                    // handling dragging events
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (isIndicator || hideInactiveStars) {
                                return@detectHorizontalDragGestures
                            }
                            onRatingChanged(lastDraggedValue)
                        },
                        onDragCancel = {
                        },
                        onDragStart = {
                        },
                        onHorizontalDrag = { change, _ ->
                            if (isIndicator || hideInactiveStars) {
                                return@detectHorizontalDragGestures
                            }
                            change.consume()
                            val dragX = change.position.x.coerceIn(-1f, rowSize.width)
                            var calculatedStars =
                                RatingBarUtils.calculateStars(
                                    dragX,
                                    paddingInPx,
                                    numOfStars,
                                    widthPx,
                                )

                            if (direction == LayoutDirection.Rtl) {
                                calculatedStars = numOfStars - calculatedStars
                            }
                            onValueChange(calculatedStars)
                            lastDraggedValue = calculatedStars
                        },
                    )
                }
                .pointerInteropFilter {
                    if (isIndicator || hideInactiveStars) {
                        return@pointerInteropFilter false
                    }
                    // handling when click events
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            val dragX = it.x.coerceIn(-1f, rowSize.width)
                            var calculatedStars =
                                RatingBarUtils.calculateStars(
                                    dragX,
                                    paddingInPx,
                                    numOfStars,
                                    widthPx,
                                )
                            if (direction == LayoutDirection.Rtl) {
                                calculatedStars = numOfStars - calculatedStars
                            }
                            onValueChange(calculatedStars)
                            onRatingChanged(calculatedStars)
                        }
                    }
                    true
                },
        ) {
            RatingStars(
                width = width,
                numOfStars = numOfStars,
                value = value,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    var rating by remember { mutableStateOf(3.3f) }
    RatingBar(
        value = rating,
        onValueChange = {
            rating = it
        },
    ) {
        Log.d("TAG", "RatingBarPreview: $it")
    }
}
