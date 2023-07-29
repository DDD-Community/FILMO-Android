package com.ddd.filmo.designsystem.component.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.ddd.filmo.designsystem.theme.FilmoColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoAppBar(
    actions: @Composable (RowScope.() -> Unit),
    navigationIcon: @Composable () -> Unit,
) {
    TopAppBar(
        actions = actions,
        navigationIcon = navigationIcon,
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = FilmoColor.Background,
            actionIconContentColor = FilmoColor.ic_01,
            navigationIconContentColor = FilmoColor.ic_01,
        ),
    )
}
