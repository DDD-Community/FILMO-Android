package com.ddd.filmo.presentation.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun SettingWebViewScreenRoute(webViewUrl: String, title: String) {
    SettingWebViewScreen(webViewUrl, title)
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SettingWebViewScreen(webviewUrl: String, title: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilmoAppBar(
            actions = {},
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
            title = title,
        )
        val state =
            rememberWebViewState("https://agreeable-vault-67f.notion.site/FAQ-9fce12857e564ad7b1551c47f76a5f8c?pvs=4")

        WebView(
            modifier = Modifier.fillMaxSize(),
            onCreated = { it.settings.javaScriptEnabled = true },
            state = state,
        )
    }
}

@Preview
@Composable
fun SettingWebViewScreenPreview() {
    SettingWebViewScreen(webviewUrl = "webviewUrl", title = "")
}
