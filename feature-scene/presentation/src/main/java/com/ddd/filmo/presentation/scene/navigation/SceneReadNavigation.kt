package com.ddd.filmo.presentation.scene.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val sceneDetailRoute = "scene_detail_route"

fun NavController.navigateSceneDetail() {
//    val encodedId = Uri.encode(topicId)
    this.navigate(sceneDetailRoute)
}

fun NavGraphBuilder.SceneDetailScreen(
    navigateToReadVote: (Int) -> Unit,
    backToMain: () -> Unit,
) {
    composable(
        route = sceneDetailRoute,
        arguments = listOf(),
    ) {
        SceneReadScreen()
    }
}