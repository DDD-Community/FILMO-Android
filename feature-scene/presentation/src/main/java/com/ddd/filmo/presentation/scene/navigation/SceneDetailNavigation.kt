package com.ddd.filmo.presentation.scene.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ddd.filmo.model.Scene
import com.ddd.filmo.presentation.scene.ui.read.SceneReadScreenRoute
import java.util.Date

internal const val sceneReadRoute = "scene_read_route"

fun NavController.navigateSceneRead() {
//    val encodedId = Uri.encode(topicId)
    this.navigate(sceneReadRoute)
}

fun NavGraphBuilder.SceneReadScreen(
//    navigateToReadVote: (Int) -> Unit,
//    backToMain: () -> Unit,
) {
    composable(
        route = sceneReadRoute,
        arguments = listOf(),
    ) {
        // todo 테스트로 작성해놓은것으로 후에 반드시 수정할것
        SceneReadScreenRoute(
            scene = Scene(
                sceneId = null,
                sceneText = null,
                sceneType = null,
                sceneRate = null,
                movie = null,
                createdAt = Date(),
            ),
        )
    }
}
