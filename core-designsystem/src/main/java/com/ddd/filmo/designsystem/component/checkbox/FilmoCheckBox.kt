package com.ddd.filmo.designsystem.component.checkbox

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
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
        colors = CheckboxColors(
            checkedBorderColor = FilmoColor.Primary,
            uncheckedBoxColor = FilmoColor.Background2,
            uncheckedBorderColor = FilmoColor.Primary,
            checkedCheckmarkColor = FilmoColor.txt_01,
            uncheckedCheckmarkColor = FilmoColor.txt_01,
            checkedBoxColor = FilmoColor.Primary,
            disabledCheckedBoxColor = FilmoColor.Background2,
            disabledUncheckedBoxColor = FilmoColor.Background2,
            disabledUncheckedBorderColor = FilmoColor.Background2,
            disabledBorderColor = FilmoColor.Background2,
            disabledIndeterminateBorderColor = FilmoColor.Background2,
            disabledIndeterminateBoxColor = FilmoColor.Background2,
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
