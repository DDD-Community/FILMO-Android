package com.ddd.filmo.designsystem.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@Composable
fun FilmoDialog() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Text(
                text = "정말 로그아웃하시겠어요?",
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(700),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.2.sp,
                ),
            )
        }
    }
}

@Preview
@Composable
fun FilmoDialogPreview() {
    FilmoDialog()
}
