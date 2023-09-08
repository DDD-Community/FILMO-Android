package com.ddd.filmo.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.component.text.FilmoAutoResizeText
import com.ddd.filmo.designsystem.component.text.FontSizeRange
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

// Todo data class로 만드는게 좋을까? 분리하는게 좋을까?
/**
 *  Filmo Dialog
 *
 * @param acceptColors
 * @param cancelColors
 * @param onAcceptClicked
 * @param onCancelClicked
 */
@Composable
fun FilmoDialog(
    cancelText: String = "",
    acceptText: String = "",
    onAcceptClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    acceptColors: ButtonColors = ButtonDefaults.buttonColors(),
    cancelColors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable () -> Unit = {},
) {
    Dialog(onDismissRequest = onCancelClicked) {
        Column(
            modifier = Modifier.fillMaxWidth().background(FilmoColor.Background)
                .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content()
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FilmoButton(
                    modifier = Modifier.weight(1f).heightIn(48.dp),
                    buttonColors = cancelColors,
                    onClick = onCancelClicked,
                ) {
                    FilmoAutoResizeText(
                        text = cancelText,
                        fontSizeRange = FontSizeRange(12.sp, 16.sp),
                        color = FilmoColor.txt_01,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = FilmoFamily,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.1.sp,
                        ),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilmoButton(
                    modifier = Modifier.weight(1f).heightIn(48.dp),
                    buttonColors = acceptColors,
                    onClick = onAcceptClicked,
                ) {
                    FilmoAutoResizeText(
                        text = acceptText,
                        fontSizeRange = FontSizeRange(12.sp, 16.sp),
                        color = FilmoColor.txt_01,
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
            }
        }
    }
}

@Preview
@Composable
fun FilmoDialogPreview() {
    FilmoDialog(content = {}, onCancelClicked = {}, onAcceptClicked = {})
}
