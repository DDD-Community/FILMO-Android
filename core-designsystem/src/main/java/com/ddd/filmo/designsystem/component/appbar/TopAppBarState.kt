package com.ddd.filmo.designsystem.component.appbar // ktlint-disable filename

import androidx.compose.runtime.Composable

data class FilmoTopAppBarState(
    val title: String? = null,
    val onAction: @Composable (() -> Unit)? = null,
    val onNavigation: @Composable (() -> Unit)? = null,
) {
    val isAllEmpty: Boolean
        get() = onAction == null && onNavigation == null && title == null
}
