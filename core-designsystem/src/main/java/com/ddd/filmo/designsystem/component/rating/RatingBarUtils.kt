package com.ddd.filmo.designsystem.component.rating

object RatingBarUtils {

    fun calculateStars(
        draggedX: Float,
        horizontalPaddingInPx: Float,
        numOfStars: Int,
        starSizeInPx: Float,
    ): Float {
        if (draggedX <= 0) {
            return 0f
        }

        val starWidthWithRightPadding = starSizeInPx + (2 * horizontalPaddingInPx)
        val halfStarWidth = starSizeInPx / 2
        for (i in 1..numOfStars) {
            if (draggedX < (i * starWidthWithRightPadding)) {
                val crossedStarsWidth = (i - 1) * starWidthWithRightPadding
                val remainingWidth = draggedX - crossedStarsWidth
                return if (remainingWidth <= halfStarWidth) {
                    i.toFloat().minus(0.5f)
                } else {
                    i.toFloat()
                }
            }
        }
        return 0f
    }
}
