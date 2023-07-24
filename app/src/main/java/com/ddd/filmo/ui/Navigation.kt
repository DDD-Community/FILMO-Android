package com.ddd.filmo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ddd.filmo.designsystem.component.appbar.FilmoTopAppBarState
import com.ddd.filmo.main.MainScreen
import com.ddd.filmo.presentation.login.ui.LoginScreen
import com.ddd.filmo.presentation.mypage.ui.MyPageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold() { padding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(padding),
        ) {
            composable("main") {
                MainScreen()
            }
            composable("mypage") {
                var count by remember {
                    mutableStateOf(0)
                }

                MyPageScreen(
                    count = count,
                )
            }
        }
    }
}
