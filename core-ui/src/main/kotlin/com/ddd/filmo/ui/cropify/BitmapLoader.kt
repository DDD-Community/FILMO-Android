package com.ddd.filmo.ui.cropify

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * 샘플링을 사용하여 최적화된 비트맵을 로드하는 함수
 *
 * @param context
 * @param uri
 * @return
 */
internal suspend fun loadSampledBitmap(
    context: Context,
    uri: Uri,
    requireSize: IntSize,
): SampledImageBitmap = withContext(Dispatchers.IO) {
    val resolver = context.contentResolver

    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
        BitmapFactory.decodeStream(resolver.openInputStream(uri), Rect(), this)
    }

    val inSampleSize =
        calculateInSampleSize(IntSize(options.outWidth, options.outHeight), requireSize)
    options.apply {
        inJustDecodeBounds = false
        this.inSampleSize = inSampleSize
    }
    BitmapFactory.decodeStream(resolver.openInputStream(uri), Rect(), options)
        ?.let { SampledImageBitmap(it.asImageBitmap(), inSampleSize) }
        ?: throw IOException("Failed to decode stream.")
}

/**
 * 이미지를 서브 샘플링 하는 함수
 *
 * @param imageSize 원래 크기
 * @param requireSize 우리가 원하는 크기
 * @return
 */
internal fun calculateInSampleSize(imageSize: IntSize, requireSize: IntSize): Int {
    var inSampleSize = 1

    // Raw height and width of image
    if (imageSize.height > requireSize.height || imageSize.width > requireSize.width) {
        val halfHeight: Int = imageSize.height / 2
        val halfWidth: Int = imageSize.width / 2

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while (halfHeight / inSampleSize >= requireSize.height && halfWidth / inSampleSize >= requireSize.width) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}

internal data class SampledImageBitmap(val imageBitmap: ImageBitmap, val inSampleSize: Int)
