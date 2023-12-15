package com.ddd.filmo.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
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
import com.ddd.filmo.presentation.movie.SearchMovieScreenRoute
import com.ddd.filmo.presentation.mypage.ui.MyPageScreenRoute
import com.ddd.filmo.presentation.scene.ui.create.SceneCreateScreenRoute
import com.ddd.filmo.presentation.scene.ui.detail.SceneDetailScreenRoute
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

    Scaffold(

        contentWindowInsets = WindowInsets.navigationBars
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(padding)
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
                    }
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
                    }
                )
            }

            composable("filmDetail") {
                FilmDetailScreenRoute(
                    navigateToSceneDetail = {
                        navController.navigate("sceneDetail")
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            composable("movieSearch") {
                SearchMovieScreenRoute(
                    navigateToBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable("sceneDetail") {
                SceneDetailScreenRoute(
                    navigateToBack = {
                        navController.popBackStack()
                    },
                    navigateToSearch = {
                        navController.navigate("movieSearch")
                    }
                )
            }
            composable("sceneCreate") {
                SceneCreateScreenRoute(navigateToSth = { navController.navigateUp() }, navigateToBack = {
                    navController.popBackStack()
                })
            }
            composable("mypage") {
                MyPageScreenRoute(
                    navigateToSetting = {
                        navController.navigate("setting")
                    },
                    navigateToBack = {
                        navController.popBackStack()
                    }

                )
            }
            composable("setting") {
                SettingScreenRoute(
                    navigateToWebView = { url, title ->
                        navController.navigate(
                            "webview/${
                                URLEncoder.encode(
                                    url,
                                    StandardCharsets.UTF_8.toString()
                                )
                            }/$title"
                        )
                    },
                    navigateToLicence = {
                        navController.navigate("license")
                    },
                    navigateToWithdrawal = {
                        navController.navigate("withdrawal")
                    },
                    navigateToBack = {
                        navController.popBackStack()
                    }

                )
            }

            composable("license") {
                LicenseScreenRoute(
                    navigateToBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                "webview/{url}/{title}",
                arguments = listOf(
                    navArgument("url") { defaultValue = "user1234" },
                    navArgument("title") { defaultValue = "title" }
                )
            ) { backStackEntry ->
                SettingWebViewScreenRoute(
                    backStackEntry.arguments?.getString("url")!!,
                    backStackEntry.arguments?.getString("title")!!,
                    navigateToBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable("signup") {
                SignupScreenRoute(
                    navigateToMain = {
                        navController.navigate("main")
                    }
                )
            }
            composable("withdrawal") {
                WithdrawalScreenRoute(
                    navigateToBack = {
                        navController.popBackStack()
                    }

                )
            }
        }
    }
}
