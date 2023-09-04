package com.ddd.filmo.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddd.filmo.designsystem.component.textfield.FilmoOutlinedTextField
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadSceneBottomSeatDialog() {
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
        Text(
            text = "씬 가져오기",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(500),
                color = FilmoColor.txt_01,
            ),
        )
        FilmoOutlinedTextField(value = "", placeholderText = "")
    }
}

@Composable
fun LoadSceneDetail() {
    Row {
        Column {
            Text(
                text = "이 영화는 내가 봤던 영화 중 정말 기억에 남는 영화였다. 결...",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_01,
                ),
            )
            Text(
                text = "어바웃타임",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 18.2.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = FilmoColor.txt_02,
                ),
            )
            Row {
                Text(
                    text = "23.06.08",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                    ),
                )
                Image(painter = painterResource(FilmoIcon.Location), contentDescription = "")
                Text(
                    text = "기본 필름",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                    ),
                )
            }
        }
        Box {
            AsyncImage(
                model = "",
                modifier = Modifier.clip(RoundedCornerShape(13.dp)),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview
@Composable
fun LoadSceneBottomSeatDialogPreview() {
    LoadSceneBottomSeatDialog()
}

@Preview
@Composable
fun LoadSceneDetailPreview() {
    LoadSceneDetail()
}
