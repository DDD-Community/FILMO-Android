package com.ddd.filmo.designsystem.component.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.filmo.designsystem.theme.FilmoColor

@Composable
fun FilmoCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Checkbox(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            uncheckedColor = FilmoColor.Primary,
            checkedColor = FilmoColor.Primary,
            checkmarkColor = FilmoColor.txt_01,
        ),
    )
}

@Preview
@Composable
fun FilmoCheckBoxPreview() {
    Column {
        FilmoCheckBox(checked = true, onCheckedChange = {})
        FilmoCheckBox(checked = false, onCheckedChange = {})
    }
}
