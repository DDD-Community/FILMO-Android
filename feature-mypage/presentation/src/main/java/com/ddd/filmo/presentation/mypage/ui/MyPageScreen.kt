package com.ddd.filmo.presentation.mypage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.model.GoogleUser
import com.ddd.filmo.model.LoginType
import com.ddd.filmo.ui.FilmCase

@Composable
fun MyPageScreenRoute(
    navigateToSetting: () -> Unit = {},
) {
    MyPageScreen(onSettingButtonClicked = navigateToSetting)
}

@Composable
internal fun MyPageScreen(onSettingButtonClicked: () -> Unit = {}) {
    Column {
        FilmoAppBar(actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = FilmoIcon.Group), contentDescription = "")
            }
            IconButton(onClick = onSettingButtonClicked) {
                Icon(painter = painterResource(id = FilmoIcon.Setting), contentDescription = "")
            }
        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
            }
        })

        Column(
            Modifier
                .fillMaxSize()
                .background(FilmoColor.Background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(2f))
            var nameLogin by remember { mutableStateOf("Compose") }
            FilmCase(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 102.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "밍밍쟈",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 30.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LoginTypeButton(modifier = Modifier, loginType = GoogleUser.user.loginType)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = GoogleUser.user.email,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                        letterSpacing = 0.14.sp,
                    ),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(size = 20.dp),
                colors = CardDefaults.cardColors(containerColor = FilmoColor.Background2),
            ) {
                Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    MyPageDetailColumn()
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .padding(vertical = 24.dp)
                            .background(FilmoColor.txt_03),
                    )
                    MyPageDetailColumn()
                }
            }
        Spacer(modifier = Modifier.weight(5f))
        }
    }
}

@Composable
private fun LoginTypeButton(
    modifier: Modifier = Modifier,
    loginType: LoginType,
) {
    when (loginType) {
        LoginType.GOOGLE -> {
            Surface(
                modifier = modifier.size(28.dp),
                color = Color.White,
                shape = CircleShape,
            ) {
                Image(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(id = FilmoIcon.Google),
                    contentDescription = "",
                    contentScale = ContentScale.None,
                )
            }
        }

        LoginType.KAKAO -> {
            Surface(
                modifier = modifier.size(28.dp),
                color = FilmoColor.kakao_color,
                shape = CircleShape,
            ) {
                Image(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(id = FilmoIcon.Kakao),
                    contentDescription = "",
                    contentScale = ContentScale.None,
                )
            }
        }
    }
}

@Composable
private fun MyPageDetailColumn() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "13",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 33.6.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(500),
                color = FilmoColor.txt_01,
                textAlign = TextAlign.Center,
                letterSpacing = 0.24.sp,
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "내 필름",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontFamily = FilmoFamily,
            ),
            fontWeight = FontWeight(400),
            color = FilmoColor.txt_02,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun MyPageScreenPrev() {
    MyPageScreen()
}
