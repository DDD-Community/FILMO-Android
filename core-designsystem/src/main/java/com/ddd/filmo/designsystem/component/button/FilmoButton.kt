package com.ddd.filmo.designsystem.component.button

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilmoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = buttonColors,
        onClick = onClick,
    ) {
        content()
    }
}

@Preview
@Composable
fun FilmoButtonPreview() {
    FilmoButton(onClick = {}) {
        Text(text = "호호")
    }
}