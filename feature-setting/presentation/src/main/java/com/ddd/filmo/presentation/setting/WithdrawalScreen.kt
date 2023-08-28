package com.ddd.filmo.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.checkbox.FilmoCheckBox
import com.ddd.filmo.designsystem.component.dialog.FilmoDialog
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme

@Composable
fun WithdrawalScreenRoute() {
    WithdrawalScreen()
}

@Composable
fun WithdrawalScreen() {
    val checkBoxUiModelListState = remember {
        mutableStateListOf(
            WithdrawalCheckBoxUiModel(
                text = "탈퇴 시 프로필과 씬, 필름 등의 \n모든 기록과 정보가 삭제됩니다.",
                checked = false,
            ),
            WithdrawalCheckBoxUiModel(
                text = "탈퇴로 인해 삭제된 정보는\n" +
                    "복구가 불가능 합니다.",
                checked = false,
            ),
        )
    }
    val isEnabled = checkBoxUiModelListState.all { it.checked }
    var isDialogShow by remember { mutableStateOf(false) }

    if (isDialogShow) {
        WithdrawDialog(
            onAcceptClicked = {},
            onCancelClicked = {
                isDialogShow = false
            },
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(FilmoColor.Background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        FilmoAppBar(
            actions = {},
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
                }
            },
            title = "탈퇴하기",
        )

        Text(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 24.dp),
            text = "탈퇴 전 확인해 주세요.",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(600),
                color = Color(0xFFF4F4F4),
                letterSpacing = 0.24.sp,
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        checkBoxUiModelListState.forEachIndexed { index, withdrawalCheckBoxUiModel ->
            CheckBoxList(
                modifier = Modifier.padding(horizontal = 14.dp),
                text = withdrawalCheckBoxUiModel.text,
                checked = withdrawalCheckBoxUiModel.checked,
                onCheckedChange = {
                    checkBoxUiModelListState[index] =
                        checkBoxUiModelListState[index].copy(checked = it)
                },
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        FilmoButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(min = 52.dp),
            onClick = { isDialogShow = true },
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = FilmoColor.PrimaryDisabled,
                containerColor = FilmoColor.Primary,
                contentColor = FilmoColor.txt_01,
                disabledContentColor = FilmoColor.txt_02,
            ),
            enabled = isEnabled,
        ) {
            Text(
                text = "탈퇴하기",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.16.sp,
                ),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CheckBoxList(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(modifier = modifier) {
        FilmoCheckBox(checked = checked, onCheckedChange = onCheckedChange)
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = Color(0xFFF4F4F4),
                letterSpacing = 0.16.sp,
            ),
        )
    }
}

@Preview
@Composable
fun CheckBoxListPreview() {
    CheckBoxList(text = "", checked = false, onCheckedChange = {})
}

@Preview
@Composable
fun SignOutScreenPreview() {
    WithdrawalScreen()
}

@Preview
@Composable
fun WithdrawDialogPreview() {
    FilmoTheme {
        WithdrawDialog()
    }
}

@Composable
internal fun WithdrawDialog(onAcceptClicked: () -> Unit = {}, onCancelClicked: () -> Unit = {}) {
    FilmoDialog(
        content = "정말 탈퇴하시겠어요?",
        onAcceptClicked = onAcceptClicked,
        onCancelClicked = onCancelClicked,
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

@Stable
data class WithdrawalCheckBoxUiModel(
    val text: String,
    val checked: Boolean,
)
