package com.ddd.filmo.designsystem.component.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoAppBar(
    actions: @Composable (RowScope.() -> Unit)?,
    navigationIcon: @Composable () -> Unit,
    title: String = "",
) {
    TopAppBar(
        actions = actions ?: {},
        navigationIcon = navigationIcon,
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = FilmoColor.txt_01,
                ),
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = FilmoColor.Background,
            actionIconContentColor = FilmoColor.ic_01,
            navigationIconContentColor = FilmoColor.ic_01,
        ),
    )
}
