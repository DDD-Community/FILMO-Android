package com.ddd.filmo.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * ViewModel로 인해 Navigate를 호출할때 사용한다.
 *
 * @param isNavigate
 * @param content
 */
@Composable
fun NavigateEffect(isNavigate: Boolean, content: () -> Unit) {
    LaunchedEffect(isNavigate) {
        if (isNavigate) {
            content()
        }
    }
}
