package com.ddd.filmo.presentation.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.filmo.designsystem.component.appbar.FilmoAppBar
import com.ddd.filmo.designsystem.component.button.FilmoButton
import com.ddd.filmo.designsystem.icon.FilmoIcon
import com.ddd.filmo.designsystem.theme.FilmoColor
import com.ddd.filmo.designsystem.theme.FilmoFamily
import com.ddd.filmo.designsystem.theme.FilmoTheme
import de.apuri.physicslayout.lib.PhysicsLayout
import de.apuri.physicslayout.lib.physicsBody

@Composable
fun SignupScreenRoute(
    modifier: Modifier = Modifier,
) {
    // todo Test이니 Navigation으로 하든 바꿀것
    var test by remember { mutableStateOf(0) }

    when (test) {
        0 -> InsertNickNameScreen(
            modifier = modifier,
            onSignUpButtonClicked = { test++ },
        )

        else -> {
            SignupSuccessScreen()
        }
    }
}

@Composable
internal fun InsertNickNameScreen(
    modifier: Modifier = Modifier,
    onSignUpButtonClicked: () -> Unit = {},
) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = FilmoColor.Background)
            .padding(horizontal = 16.dp),
    ) {
        FilmoAppBar(actions = null, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = FilmoIcon.Back), contentDescription = "")
            }
        })

        Text(
            text = "필모에서 사용할 \n닉네임을 입력해 주세요.",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = FilmoFamily,
            ),
            fontWeight = FontWeight(600),
            color = FilmoColor.txt_01,
            letterSpacing = 0.24.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "닉네임은 2자 이상 10자 이하의\n한글, 영문, 숫자만 사용할 수 있어요.",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = FilmoColor.txt_02,
                letterSpacing = 0.12.sp,
            ),
        )
        Spacer(modifier = Modifier.height(48.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(8.dp),
            placeholder = {
                Text(
                    text = "닉네임을 입력해주세요.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_02,
                    ),
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = FilmoColor.Background3,
                unfocusedIndicatorColor = FilmoColor.Background3,
                focusedContainerColor = FilmoColor.Background3,
                unfocusedContainerColor = FilmoColor.Background3,
            ),
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = FilmoFamily,
                fontWeight = FontWeight(400),
                color = FilmoColor.txt_02,
            ),
        )
        Spacer(modifier = Modifier.weight(1f))
        FilmoButton(
            modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            onClick = { onSignUpButtonClicked() },
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = FilmoColor.PrimaryDisabled,
                containerColor = FilmoColor.Primary,
                contentColor = FilmoColor.txt_01,
                disabledContentColor = FilmoColor.txt_02,
            ),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "필모 이용하러 가기",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF4F4F4),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.16.sp,
                ),
            )
        }
    }
}

@Composable
fun SignupSuccessScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = 100f
                },
            color = FilmoColor.Background,
        ) {
            PhysicsLayoutScreen()
        }
        Column(
            modifier
                .fillMaxWidth()
                .background(color = FilmoColor.Background)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            Text(
                text = "회원가입이 완료되었어요!",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(600),
                    color = FilmoColor.txt_01,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "이제 필모를 이용할 수 있어요!\n" +
                    "필모로 나만의 필름을 만들어 보세요.",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontFamily = FilmoFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFB6B6B6),
                    textAlign = TextAlign.Center,
                ),
            )
            Spacer(modifier = Modifier.height(48.dp))
            FilmoButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp),
                onClick = { /*TODO*/ },
                buttonColors = ButtonDefaults.buttonColors(
                    disabledContainerColor = FilmoColor.PrimaryDisabled,
                    containerColor = FilmoColor.Primary,
                    contentColor = FilmoColor.txt_01,
                    disabledContentColor = FilmoColor.txt_02,
                ),
            ) {
                Text(text = "회원가입 완료", Modifier.padding(vertical = 15.dp))
            }
//            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Preview
@Composable
fun SignSuccessScreenPreview() {
    SignupSuccessScreen(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
private fun DefaultInsertNickNamePreview() {
    FilmoTheme {
        InsertNickNameScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitInsertNickNamePreview() {
    FilmoTheme {
        InsertNickNameScreen()
    }
}

@Preview
@Composable
fun Test2Preview() {
    Surface(shape = CircleShape) {
        PhysicsLayout() {
            Icon(
                modifier = Modifier.padding(18.dp),
                painter = painterResource(id = FilmoIcon.Heart),
                tint = Color.Blue,
                contentDescription = "",
            )
        }
    }
}

@Preview
@Composable
fun PhysicsLayoutScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        PhysicsLayoutScreen()
    }
}

@Composable
private fun PhysicsLayoutScreen() {
    PhysicsLayout {
        Layout(
            content = {
                PhysicsHeart(color = Color(0xFFCF68FF))

                PhysicsHeart(color = Color(0xFFCF68FF))

                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                        textAlign = TextAlign.Center,
                    ),
                    text = "My Best",
                    color = FilmoColor.Background3,
                )

                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = FilmoColor.txt_01,
                        textAlign = TextAlign.Center,
                    ),
                    text = "인생 영화",
                    color = FilmoColor.Background3,
                )
                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF4F4F4),
                        textAlign = TextAlign.Center,
                    ),
                    text = "필모",
                    color = FilmoColor.Background3,
                )

                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF272627),
                        textAlign = TextAlign.Center,
                    ),
                    text = "“ Please, let me keep this memory \n just this moment.”",
                    color = Color(0xFFFF97CA),
                )

                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF272627),
                        textAlign = TextAlign.Center,
                    ),
                    text = "겨울마다 보는 내 인생 뮤지컬 영화",
                    Color(0xFFFFCE4F),
                )

                PhysicsHeart(color = FilmoColor.ic_02)
                PhysicsCard(
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = FilmoFamily,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF272627),
                        textAlign = TextAlign.Center,
                    ),
                    text = "힐링이 필요할 때",
                    Color(0xFFBBEF4C),
                )

                PhysicsHeart(color = Color.Blue)
                PhysicsHeart(color = Color(0xFFEE0072))
            },
        ) { measurables, constraints ->
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0,
            )

            val placeables = measurables.map { measurable ->
                measurable.measure(constraints = looseConstraints)
            }
            layout(looseConstraints.maxWidth, looseConstraints.maxHeight) {
                placeables.forEach { placeable ->
                    placeable.place(0, 0)
                }
            }
        }
    }
}

@Composable
private fun BoxScope.PhysicsCard(textStyle: TextStyle, text: String, color: Color) {
    Card(
        Modifier
            .physicsBody(
                shape = RoundedCornerShape(25),
            )
            .align(Alignment.CenterEnd),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(50),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            text = text,
            style = textStyle,
        )
    }
}

@Composable
private fun PhysicsHeart(color: Color) {
    Card(
        modifier = Modifier
            .physicsBody(
                shape = CircleShape,
            ),
        shape = CircleShape,
    ) {
        Icon(
            modifier = Modifier.padding(18.dp),
            painter = painterResource(id = FilmoIcon.Heart),
            tint = color,
            contentDescription = "",
        )
    }
}
