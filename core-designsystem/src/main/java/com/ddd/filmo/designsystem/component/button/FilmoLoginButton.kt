package com.ddd.filmo.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@Composable
fun FilmoLoginButton(
    text: String,
    @DrawableRes drawble: Int,
    containsColor: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 42.dp),
        shape = RoundedCornerShape(6.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = containsColor),
    ) {
        Image(painter = painterResource(id = drawble), contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 15.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(600),
                color = FilmoColor.txt_04,
            ),
        )
    }
}

@Preview
@Composable
fun a() {
    FilmoLoginButton(
        "카카오로 로그인하기",
        FilmoIcon.Google,
        containsColor = FilmoColor.kakao_color,
        onClick = {},
    )
}
