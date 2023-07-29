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

package com.ddd.filmo.presentation.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.filmo.designsystem.component.button.FilmoLoginButton
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme

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
        Modifier.fillMaxSize().background(FilmoColor.Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        var nameLogin by remember { mutableStateOf("Compose") }
        Image(painter = painterResource(id = FilmoIcon.FIlmoTextLogo), contentDescription = "")
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "나만의 필름 모아",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(500),
                color = FilmoColor.txt_05,
                textAlign = TextAlign.Center,
            ),
        )
        Spacer(modifier = Modifier.height(36.dp))
        FilmoLoginButton(
            onClick = { /*TODO*/ },
            text = "카카오로 시작하기",
            drawble = FilmoIcon.Kakao,
            containsColor = Color(0xFFFEE500),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FilmoLoginButton(

            onClick = { /*TODO*/ },
            text = "구글로 시작하기",
            drawble = FilmoIcon.Google,
            containsColor = Color.White,
        )
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
