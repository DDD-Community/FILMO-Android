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

package com.ddd.filmo.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.dialog.FilmoDialog
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme
import com.ddd.filmo.presentation.setting.model.SettingEvent
import com.ddd.filmo.presentation.setting.model.SettingUiList

@Composable
fun SettingScreenRoute(navigateToMain: () -> Unit) {
    SettingScreen(navigateToMain)
}

@Composable
internal fun SettingScreen(loginButtonClicked: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        FilmoAppBar(
            actions = {},
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
            title = "설정",
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SettingUiList.forEachIndexed { index, settingUi ->
                SettingDetail(
                    text = settingUi.name,
                    onClick = { settingUi.event },
                    isLast = index == SettingUiList.lastIndex,
                )
            }
        }
    }
}

@Composable
fun SettingDetail(
    text: String,
    onClick: () -> SettingEvent = { SettingEvent.WithdrawClicked },
    isLast: Boolean,
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        },
    ) {
        Text(
            modifier = Modifier.padding(top = 15.dp, bottom = 19.dp),
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(600),
                color = FilmoColor.txt_01,
                letterSpacing = 0.16.sp,
            ),
        )
        if (!isLast) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(FilmoColor.txt_03),
            )
        }
    }
}

/**
 * 탈퇴 프리뷰
 *
 */
@Preview
@Composable
fun LogOutDialogPreview() {
    FilmoTheme {
        FilmoDialog(
            content = "정말 로그아웃하시겠어요?",
            onAcceptClicked = { /*TODO*/ },
            onCancelClicked = {},
            cancelText = "취소하기",
            acceptText = "로그아웃하기",
            cancelColors = ButtonDefaults.buttonColors(
                containerColor = FilmoColor.txt_03,
            ),
            acceptColors = ButtonDefaults.buttonColors(
                containerColor = FilmoColor.Primary,
            ),
        )
    }
}

@Preview
@Composable
fun WithdrawDialogPreview() {
    FilmoTheme {
        FilmoDialog(
            content = "정말 탈퇴하시겠어요?",
            onAcceptClicked = { /*TODO*/ },
            onCancelClicked = {},
            cancelText = "더 사용해보기",
            acceptText = "탈퇴하기",
            cancelColors = ButtonDefaults.buttonColors(
                containerColor = FilmoColor.txt_03,
            ),
            acceptColors = ButtonDefaults.buttonColors(
                containerColor = FilmoColor.film_color_05,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    FilmoTheme {
        SettingScreen()
    }
}

@Preview
@Composable
fun SettingDetailPreview() {
    SettingDetail("FAQ", isLast = false)
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitLoginPreview() {
    FilmoTheme {
        SettingScreen()
    }
}
