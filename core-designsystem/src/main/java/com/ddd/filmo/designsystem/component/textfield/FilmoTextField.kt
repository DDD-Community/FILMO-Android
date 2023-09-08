package com.ddd.filmo.designsystem.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

// todo : Container Color 컬러 시스템 구축 필요
@Composable
fun FilmoOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit = {},
    isError: Boolean = false,
    placeholderText: String,
    containerColor: Color = FilmoColor.Background3,
    leadingType: FilmoTextFieldLeadingType = FilmoTextFieldLeadingType.NONE,
    trailingType: FilmoTextFieldTrailingType = FilmoTextFieldTrailingType.NONE,
) {
    val trailingTypeLambda: @Composable (() -> Unit)? = when (trailingType) {
        FilmoTextFieldTrailingType.NONE -> null
        FilmoTextFieldTrailingType.CLEAR -> {
            {
                if (value.isNotEmpty()) {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(FilmoIcon.X),
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    }

    val leadingTypeLambda: @Composable (() -> Unit)? = when (leadingType) {
        FilmoTextFieldLeadingType.NONE -> null
        FilmoTextFieldLeadingType.SEARCH -> {
            {
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(FilmoIcon.Search),
                        contentDescription = "",
                    )
                }
            }
        }
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        value = value,
        onValueChange = onValueChanged,
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = placeholderText,
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
            focusedIndicatorColor = containerColor,
            unfocusedIndicatorColor = containerColor,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            errorIndicatorColor = FilmoColor.Error,
        ),
        leadingIcon = leadingTypeLambda,
        trailingIcon = trailingTypeLambda,
        textStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 22.4.sp,
            fontFamily = FilmoFamily,
            fontWeight = FontWeight(400),
            color = FilmoColor.txt_02,
        ),
    )
}

enum class FilmoTextFieldLeadingType {
    NONE, SEARCH,
}

enum class FilmoTextFieldTrailingType {
    NONE, CLEAR,
}
