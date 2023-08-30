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

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ddd.filmo.designsystem.component.button.FilmoLoginButton
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme
import com.ddd.filmo.feature.mypage.R
import com.ddd.filmo.model.SceneType
import com.ddd.filmo.ui.SceneImage
import com.ddd.filmo.ui.SceneImageTest.firstSceneType
import com.ddd.filmo.ui.SceneImageTest.secondSceneType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.delay

@Composable
fun LoginScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToSign: () -> Unit,
) {
    val loginUiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
//                    viewModel.fetchSignInUser(gsa.email, gsa.displayName)
                } else {
                    viewModel.setErrorMessage("에러를 확인 해주세요")
                }
            } catch (e: ApiException) {
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }

    LoginScreen(onLoginSuccess = {
        when (it) {
            LoginType.GOOGLE -> {
                authResultLauncher.launch(signInRequestCode)
            }

            LoginType.KAKAO -> {
            }
        }
    }, onTestNeeded = navigateToSign)
}

@Composable
internal fun LoginScreen(onLoginSuccess: (LoginType) -> Unit = {}, onTestNeeded: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AutoSlideColumn(
            content = {
                firstSceneType.map {
                    if (it.sceneType is SceneType.Color) {
                        SceneImage(
                            scene = it,
                            navigateToSceneDetail = {},
                            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                            textColor = Color.Black,
                        )
                    } else {
                        SceneImage(
                            scene = it,
                            navigateToSceneDetail = {},
                            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                        )
                    }
                }
            },
            direction = true,
        )

        AutoSlideColumn(
            content = {
                secondSceneType.map {
                    if (it.sceneType is SceneType.Color) {
                        SceneImage(
                            scene = it,
                            navigateToSceneDetail = {},
                            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                            textColor = Color.Black,
                        )
                    } else {
                        SceneImage(
                            scene = it,
                            navigateToSceneDetail = {},
                            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                        )
                    }
                }
            },
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
            onClick = onTestNeeded,
            text = "카카오로 시작하기",
            drawble = FilmoIcon.Kakao,
            containsColor = Color(0xFFFEE500),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FilmoLoginButton(
            onClick = { onLoginSuccess(LoginType.GOOGLE) },
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
) {
    var xPositionState by remember { mutableStateOf(listOf<Int>()) }

    var lifecycleEvent by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME && xPositionState.isNotEmpty()) {
            while (true) {
                xPositionState = xPositionState.map { if (direction) it + 1 else it - 1 }
                delay(10)
            }
        }
    }

    Layout(content = content, modifier = Modifier) { measurables, constraints ->
        val halfConstraints = constraints.copy(maxWidth = constraints.maxWidth / 4 * 3)
        val placeables = measurables.map { measurable ->
            measurable.measure(halfConstraints)
        }

        layout(constraints.maxWidth, placeables.first().height) {
            val totalWidth = placeables.sumOf { it.width }

            var xPosition =
                if (xPositionState.isEmpty()) {
                    if (direction) (constraints.maxWidth - totalWidth) else (-constraints.maxWidth + totalWidth)
                } else {
                    xPositionState.last()
                }
            var yPosition = 0
            var xPositionList = IntArray(placeables.size) { 0 }

            if (xPositionState.isEmpty()) {
                placeables.forEachIndexed { index, placeable ->
                    placeable.placeRelative(xPosition, yPosition)
                    if (direction) xPosition += placeable.width else xPosition -= placeable.width
                    xPositionList.set(index, xPosition)
                }
                xPositionState = xPositionList.toList()
            } else {
                placeables.forEachIndexed { index, placeable ->
                    placeable.placeRelative(xPositionState[index], yPosition)
                    if (direction) {
                        if (xPositionState[index] > constraints.maxWidth) {
                            xPositionList[index] = xPositionState.min() - placeable.width
                        } else {
                            xPositionList[index] = xPositionState[index]
                        }
                    } else {
                        if (xPositionState[index] < -placeable.width) {
                            xPositionList[index] = xPositionState.max() + placeable.width
                        } else {
                            xPositionList[index] = xPositionState[index]
                        }
                    }
                }
                xPositionState = xPositionList.toList()
            }
        }
    }
}

// 이전에 로그인 한 계정이 있는지 확인
private fun getLastSignedInAccount(context: Context) = GoogleSignIn.getLastSignedInAccount(context)

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

private fun getGoogleLoginAuth(context: Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(R.string.google_cloud_server_client_id))
        .requestId()
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(context, gso)
}

enum class LoginType {
    GOOGLE,
    KAKAO,
}
