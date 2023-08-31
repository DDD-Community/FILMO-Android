package com.ddd.filmo.designsystem.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@Composable
fun FilmoOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit = {},
    isError: Boolean = false,
    placeholderText: String,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        value = value,
        onValueChange = onValueChanged,
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
        isError = isError,
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
}
