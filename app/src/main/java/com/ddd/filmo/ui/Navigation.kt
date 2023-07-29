package com.ddd.filmo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ddd.filmo.main.MainScreen
import com.ddd.filmo.presentation.film.detail.FilmDetailScreen
import com.ddd.filmo.presentation.login.ui.LoginScreenRoute
import com.ddd.filmo.presentation.mypage.ui.MyPageScreenRoute
import com.ddd.filmo.presentation.scene.ui.detail.SceneDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold() { padding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(padding),
        ) {
            composable("main") {
                MainScreen(
                    navigateToFilmDetail = {
                        navController.navigate("filmDetail")
                    },
                )
            }
            composable("login") {
                LoginScreenRoute(
                    navigateToMain = {
                        navController.navigate("main")
                    },
                )
            }

            composable("filmDetail") {
                FilmDetailScreen(
                    navigateToSceneDetail = {
                        navController.navigate("sceneDetail")
                    },
                )
            }
            composable("sceneDetail") {
                SceneDetailScreen()
            }
            composable("mypage") {
                var count by remember {
                    mutableStateOf(0)
                }
                MyPageScreenRoute()
            }
        }
    }
}
