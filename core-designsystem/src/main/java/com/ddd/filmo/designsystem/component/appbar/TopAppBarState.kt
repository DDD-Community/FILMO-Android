package com.ddd.filmo.designsystem.component.appbar

import androidx.compose.runtime.Composable

data class FilmoTopAppBarState(
    val title: String,
    val onAction: @Composable (() -> Unit)? = null,
    val onNavigation: @Composable (() -> Unit)? = null,
)
