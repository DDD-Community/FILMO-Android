package com.ddd.filmo.designsystem.component.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoModalBottomSheetDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = FilmoColor.Background,
        windowInsets = WindowInsets.displayCutout,
    ) {
        Column(modifier = Modifier.padding(bottom = bottomPadding)) {
            content()
        }
//
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmoChoiceBottomSheetDialog(
    choiceList: List<String>,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    CompositionLocalProvider(LocalContentColor.provides(FilmoColor.Background)) {
        FilmoModalBottomSheetDialog(
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            choiceList.forEachIndexed { index, choice ->
                FilmoChoiceBottomSheetItem(
                    text = choice,
                    onClick = {
                        onItemClicked(index)
                    },
                    color = if (choiceList.lastIndex == index) FilmoColor.txt_02 else FilmoColor.txt_01,

                )
                if (choiceList.lastIndex != index) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(FilmoColor.txt_03)
                            .height(1.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun FilmoChoiceBottomSheetItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = FilmoColor.txt_01,
) {
    Surface(
        onClick = onClick,
        color = FilmoColor.Background,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            Modifier.padding(vertical = 13.dp),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(500),
                color = color,
                textAlign = TextAlign.Center,
                letterSpacing = 0.16.sp,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FilmoChoiceBottomSheetDialogPreview() {
    FilmoChoiceBottomSheetDialog(
        choiceList = listOf("1", "2", "3"),
        onItemClicked = {},
    )
}
