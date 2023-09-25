package com.ddd.filmo.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults

@Composable
fun LicenseScreenRoute(navigateToBack: () -> Unit = {}) {
    LicenseScreen(
        onBackButtonClicked = navigateToBack,

    )
}

@Composable
fun LicenseScreen(onBackButtonClicked: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilmoAppBar(
            actions = {
            },
            navigationIcon = {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
            title = "라이센스",
        )
        LibrariesContainer(
            Modifier.fillMaxSize(),
            colors = LibraryDefaults.libraryColors(
                backgroundColor = FilmoColor.Background,
                contentColor = Color.White,
            ),
        )
    }
}

@Preview
@Composable
fun LicenseScreenPreview() {
    LicenseScreen()
}
