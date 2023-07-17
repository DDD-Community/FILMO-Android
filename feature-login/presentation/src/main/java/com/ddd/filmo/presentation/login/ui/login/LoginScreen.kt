/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ddd.filmo.presentation.login.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.filmo.designsystem.theme.LogoColor
import com.ddd.filmo.designsystem.theme.FilmoTheme
import com.ddd.filmo.designsystem.icon.FilmoIcon

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel = hiltViewModel()) {
//    val items by viewModel.uiState.collectAsStateWithLifecycle()
//    if (items is Success) {
    LoginScreen(
        items = listOf(),
        onSave = { name -> },
        modifier = modifier,
    )
//    }
}

@Composable
internal fun LoginScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        Modifier.fillMaxSize().background(LogoColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        var nameLogin by remember { mutableStateOf("Compose") }
        Image(painter = painterResource(id = FilmoIcon.FilmoLogo), contentDescription = "")
        Text(
            text = "나만의 명장면을 모아\n" +
                "필름을 만들어 보세요.\n" +
                "\n" +
                "어쩌구 저쩌고 , 필모",
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "카카오로 시작하기")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "구글로 시작하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultLoginPreview() {
    FilmoTheme {
        LoginScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitLoginPreview() {
    FilmoTheme {
        LoginScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}
