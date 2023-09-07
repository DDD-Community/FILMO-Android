package com.ddd.filmo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ddd.filmo.main.MainScreenRoute
import com.ddd.filmo.presentation.film.detail.FilmDetailScreenRoute
import com.ddd.filmo.presentation.login.ui.LoginScreenRoute
import com.ddd.filmo.presentation.mypage.ui.MyPageScreenRoute
import com.ddd.filmo.presentation.scene.ui.create.SceneCreateScreen
import com.ddd.filmo.presentation.scene.ui.detail.SceneDetailScreen
import com.ddd.filmo.presentation.setting.LicenseScreenRoute
import com.ddd.filmo.presentation.setting.SettingScreenRoute
import com.ddd.filmo.presentation.setting.SettingWebViewScreenRoute
import com.ddd.filmo.presentation.setting.WithdrawalScreenRoute
import com.ddd.filmo.presentation.signup.ui.SignupScreenRoute
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
                MainScreenRoute(
                    navigateToFilmDetail = {
                        navController.navigate("filmDetail")
                    },
                    navigateToMyPage = {
                        navController.navigate("mypage")
                    },
                    navigateToSceneCreate = {
                        navController.navigate("sceneCreate")
                    },
                )
            }
            composable("login") {
                LoginScreenRoute(
                    navigateToMain = {
                        navController.navigate("main") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }
                    },
                    navigateToSign = {
                        navController.navigate("signup")
                    },
                )
            }

            composable("filmDetail") {
                FilmDetailScreenRoute(
                    navigateToSceneDetail = {
                        navController.navigate("sceneDetail")
                    },
                )
            }
            composable("sceneDetail") {
                SceneDetailScreen()
            }
            composable("sceneCreate") {
                SceneCreateScreen()
            }
            composable("mypage") {
                MyPageScreenRoute(
                    navigateToSetting = {
                        navController.navigate("setting")
                    },

                )
            }
            composable("setting") {
                SettingScreenRoute(
                    navigateToWebView = { url, title ->
                        navController.navigate(
                            "webview/${
                                URLEncoder.encode(
                                    url,
                                    StandardCharsets.UTF_8.toString(),
                                )
                            }/$title",
                        )
                    },
                    navigateToLicence = {
                        navController.navigate("license")
                    },
                    navigateToWithdrawal = {
                        navController.navigate("withdrawal")
                    },
                )
            }

            composable("license") {
                LicenseScreenRoute()
            }

            composable(
                "webview/{url}/{title}",
                arguments = listOf(
                    navArgument("url") { defaultValue = "user1234" },
                    navArgument("title") { defaultValue = "title" },
                ),
            ) { backStackEntry ->
                SettingWebViewScreenRoute(
                    backStackEntry.arguments?.getString("url")!!,
                    backStackEntry.arguments?.getString("title")!!,
                )
            }

            composable("signup") {
                SignupScreenRoute()
            }
            composable("withdrawal") {
                WithdrawalScreenRoute()
            }
        }
    }
}
