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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.button.FilmoLoginButton
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme
import com.ddd.filmo.ui.SceneImage
import com.ddd.filmo.ui.SceneImageTest.firstSceneType
import com.ddd.filmo.ui.SceneImageTest.secondSceneType
import kotlinx.coroutines.delay

@Composable
fun LoginScreenRoute(navigateToMain: () -> Unit) {
    LoginScreen(navigateToMain)
}

@Composable
internal fun LoginScreen(loginButtonClicked: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var xCondition by remember { mutableStateOf(0) }

        LaunchedEffect(true) {
            while (true) {
                xCondition += 1
                delay(10)
            }
        }
        AutoSlideColumn(
            content = {
                firstSceneType.map {
                    SceneImage(scene = it, navigateToSceneDetail = {})
                }
            },
            scroll = xCondition,
            direction = true,
        )

        AutoSlideColumn(
            content = {
                secondSceneType.map {
                    SceneImage(scene = it, navigateToSceneDetail = {})
                }
            },
            scroll = xCondition,
            direction = false,
        )
        Spacer(modifier = Modifier.height(36.dp))
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
            onClick = loginButtonClicked,
            text = "카카오로 시작하기",
            drawble = FilmoIcon.Kakao,
            containsColor = Color(0xFFFEE500),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FilmoLoginButton(
            onClick = loginButtonClicked,
            text = "구글로 시작하기",
            drawble = FilmoIcon.Google,
            containsColor = Color.White,
        )
    }
}

@Composable
fun AutoSlideColumn(
    content: @Composable () -> Unit,
    direction: Boolean = false,
    scroll: Int = 0,
) {
    Layout(content = content, modifier = Modifier) { measurables, constraints ->
        val halfConstraints = constraints.copy(maxWidth = constraints.maxWidth / 4 * 3 )
        val placeables = measurables.map { measurable ->
            measurable.measure(halfConstraints)
        }

        layout(constraints.maxWidth, placeables.first().height) {
            val totalWidth = placeables.sumOf { it.width }

            val scrollLTR = if (direction) scroll else -scroll
            var xPosition =
                if (direction) (constraints.maxWidth - totalWidth) + scrollLTR else (-constraints.maxWidth + totalWidth) + scrollLTR
            var yPosition = 0

            placeables.forEach { placeable ->
//                if (xPosition > constraints.maxWidth) {
//                    xPosition = leftxPosition - placeable.width
//                }

                placeable.placeRelative(xPosition, yPosition)
//                if (leftxPosition < xPosition) leftxPosition = xPosition

                if (direction) xPosition += placeable.width else xPosition -= placeable.width
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultLoginPreview() {
    FilmoTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitLoginPreview() {
    FilmoTheme {
        LoginScreen()
    }
}
