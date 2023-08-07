package com.ddd.filmo.presentation.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme

@Composable
internal fun InsertNickNameScreen(
    modifier: Modifier = Modifier,
    test: String = "test",
) {
    InsertNickNameScreen(modifier = modifier)
}

@Composable
private fun InsertNickNameScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.fillMaxSize().background(color = FilmoColor.Background)
            .padding(horizontal = 16.dp),
    ) {
        FilmoAppBar(actions = null, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
            }
        })
        Text(
            text = "필모에서 사용할 \n닉네임을 입력해 주세요.",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = FilmoFamily,
            ),
            fontWeight = FontWeight(600),
            color = FilmoColor.txt_01,
            letterSpacing = 0.24.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "닉네임은 2자 이상 10자 이하의\n한글, 영문, 숫자만 사용할 수 있어요.",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = FilmoColor.txt_02,
                letterSpacing = 0.12.sp,
            ),
        )
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = "닉네임을 입력해주세요.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                    ),
                )
            },

            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = FilmoColor.Background3,
                unfocusedIndicatorColor = FilmoColor.Background3,
                focusedContainerColor = FilmoColor.Background3,
                unfocusedContainerColor = FilmoColor.Background3,
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = FilmoColor.txt_02,
            ),
        )
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = FilmoColor.PrimaryDisabled,
                containerColor = FilmoColor.Primary,
                contentColor = FilmoColor.txt_01,
                disabledContentColor = FilmoColor.txt_02,
            ),
        ) {
            Text(text = "회원가입 완료", Modifier.padding(vertical = 15.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultInsertNickNamePreview() {
    FilmoTheme {
        InsertNickNameScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitInsertNickNamePreview() {
    FilmoTheme {
        InsertNickNameScreen()
    }
}
