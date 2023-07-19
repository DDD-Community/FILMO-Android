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
import com.ddd.filmo.presentation.login.ui.LoginScreen
import com.ddd.filmo.presentation.mypage.ui.MyPageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val appState = rememberAppState()
    var filmoTopAppBarState by remember {
        mutableStateOf(
            FilmoTopAppBarState(
                "main",
                {
                    Text("action")
                },
                {
                    Text("action")
                },
            ),
        )
    }

    Scaffold(
        topBar = {
            if (!filmoTopAppBarState.isAllEmpty) {
                TopAppBar(
                    title = { filmoTopAppBarState.title?.let { Text(it) } },
                    navigationIcon = { filmoTopAppBarState.onNavigation?.invoke() },
                    actions = { filmoTopAppBarState.onAction?.invoke() },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                )
            }
        },
        bottomBar = {
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(padding),
        ) {
            composable("main") {
                filmoTopAppBarState = FilmoTopAppBarState(
                    "main",
                    {
                        Text("action")
                    },
                    {
                        Text("action")
                    },
                )
                LoginScreen(
                    modifier = Modifier.padding(16.dp),
//                    navigate = {
//                        navController.navigate(it)
//                    }
                )
            }
            composable("mypage") {
                var count by remember {
                    mutableStateOf(0)
                }

                filmoTopAppBarState = FilmoTopAppBarState(
                    "mypage",
                    {
                        Text(
                            "back",
                            modifier = Modifier.clickable {
                                navController.navigateUp()
                            },
                        )
                    },
                    {
                        Text("action")
                        count++
                    },
                )
                MyPageScreen(
                    count = count,
                )
            }
        }
    }
}
