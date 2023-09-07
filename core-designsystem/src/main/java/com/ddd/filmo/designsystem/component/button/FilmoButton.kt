package com.ddd.filmo.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilmoButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    roundedCornerShape: Shape = RoundedCornerShape(12.dp),
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier.heightIn(min = 52.dp),
        shape = roundedCornerShape,
        colors = buttonColors,
        onClick = onClick,
        enabled = enabled,
        contentPadding = contentPadding,
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
