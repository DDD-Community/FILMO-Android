package com.ddd.filmo.presentation.login.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddd.filmo.core.ui.MyApplicationTheme

@Composable
internal fun InsertNickNameScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextField(value = "", onValueChange = {}, placeholder = {
            Text(text = "닉네임")
        })

        Button(onClick = { /*TODO*/ }) {
            Text(text = "회원가입 완료")
        }
    }
}

@Composable
internal fun SuccessSignUpScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextField(value = "", onValueChange = {}, placeholder = {
            Text(text = "닉네임")
        })

        Button(onClick = { /*TODO*/ }) {
            Text(text = "회원가입 완료")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultInsertNickNamePreview() {
    MyApplicationTheme {
        InsertNickNameScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitInsertNickNamePreview() {
    MyApplicationTheme {
        InsertNickNameScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultSuccessSignUpPreview() {
    MyApplicationTheme {
        InsertNickNameScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitSuccessSignUpPreview() {
    MyApplicationTheme {
        InsertNickNameScreen()
    }
}